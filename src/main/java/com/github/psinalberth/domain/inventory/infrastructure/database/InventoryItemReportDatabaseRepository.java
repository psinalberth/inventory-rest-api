package com.github.psinalberth.domain.inventory.infrastructure.database;

import com.github.psinalberth.domain.inventory.core.model.InventoryItemReport;
import com.github.psinalberth.domain.inventory.core.ports.outgoing.InventoryItemReportRepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Named
@RequiredArgsConstructor
public class InventoryItemReportDatabaseRepository implements InventoryItemReportRepository {

    private final InventoryItemReportMongoRepository delegate;
    private final InventoryItemDatabaseMapper databaseMapper;

    @Override
    public CompletableFuture<List<InventoryItemReport>> queryItems(final String inventoryId) {
        return delegate.queryItems(inventoryId)
                .map(databaseMapper::toDomainReport)
                .collectList()
                .toFuture();
    }
}
