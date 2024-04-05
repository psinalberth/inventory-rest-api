package com.github.psinalberth.domain.inventory.infrastructure.database;

import com.github.psinalberth.domain.inventory.core.model.Inventory;
import com.github.psinalberth.domain.inventory.core.ports.outgoing.InventoryRepository;
import com.mongodb.MongoException;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Named
@RequiredArgsConstructor
public class InventoryDatabaseRepository implements InventoryRepository {

    private final InventoryMongoRepository delegate;
    private final InventoryDatabaseMapper databaseMapper;
    private final Retry retry = Retry.backoff(3, Duration.ofMillis(200))
            .filter(ex -> ex instanceof MongoException)
            .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                log.error("This operation could not be completed this time.");
                return retrySignal.failure();
            });

    @Override
    public CompletableFuture<Inventory> save(final Inventory inventory) {
        log.info("Saving inventory {}", inventory);
        return delegate.save(databaseMapper.toEntity(inventory))
                .retryWhen(retry)
                .mapNotNull(databaseMapper::toDomain)
                .toFuture();
    }

    @Override
    public CompletableFuture<Inventory> update(Inventory inventory) {
        log.info("Updating inventory {}", inventory);
        return delegate.save(databaseMapper.toEntity(inventory))
                .retryWhen(retry)
                .mapNotNull(databaseMapper::toDomain)
                .toFuture();
    }

    @Override
    public CompletableFuture<Inventory> fetch(final String code) {
        log.info("Fetching inventory with code {}", code);
        return delegate
                .findByCode(code)
                .retryWhen(retry)
                .mapNotNull(databaseMapper::toDomain)
                .toFuture();
    }
}
