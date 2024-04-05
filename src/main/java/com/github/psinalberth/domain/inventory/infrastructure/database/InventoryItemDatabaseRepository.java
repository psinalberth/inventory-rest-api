package com.github.psinalberth.domain.inventory.infrastructure.database;

import com.github.psinalberth.domain.inventory.core.model.InventoryItem;
import com.github.psinalberth.domain.inventory.core.ports.outgoing.InventoryItemRepository;
import com.mongodb.MongoException;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Named
@RequiredArgsConstructor
public class InventoryItemDatabaseRepository implements InventoryItemRepository {

    private final InventoryItemMongoRepository delegate;
    private final InventoryItemDatabaseMapper databaseMapper;

    private final Retry retry = Retry.backoff(3, Duration.ofMillis(200))
            .filter(ex -> ex instanceof MongoException)
            .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                log.error("This operation could not be completed this time.");
                return retrySignal.failure();
            });

    @Override
    public CompletableFuture<InventoryItem> save(final InventoryItem item) {
        log.info("Saving inventory item {}", item);
        return delegate.save(databaseMapper.toEntity(item))
                .retryWhen(retry)
                .mapNotNull(databaseMapper::toDomain)
                .toFuture();
    }

    @Override
    public CompletableFuture<InventoryItem> findById(
            final String inventoryId,
            final String productId,
            final String batchTypeId
    ) {
        log.info("Fetching inventory item with id {}", productId);
        return delegate.findById(new InventoryItemId(inventoryId, productId, batchTypeId))
                .retryWhen(retry)
                .mapNotNull(databaseMapper::toDomain)
                .toFuture();
    }

    @Override
    public CompletableFuture<List<InventoryItem>> findAll(final String inventoryId) {
        log.info("Fetching inventory items from inventory {}", inventoryId);
        return delegate.findAllByInventoryId(inventoryId)
                .map(databaseMapper::toDomain)
                .collectList()
                .toFuture();
    }
}
