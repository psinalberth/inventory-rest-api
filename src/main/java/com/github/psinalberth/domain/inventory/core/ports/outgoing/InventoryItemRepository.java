package com.github.psinalberth.domain.inventory.core.ports.outgoing;

import com.github.psinalberth.domain.inventory.core.model.InventoryItem;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface InventoryItemRepository {

    CompletableFuture<InventoryItem> save(final InventoryItem item);

    CompletableFuture<InventoryItem> findById(
            final String inventoryId,
            final String productId,
            final String batchTypeId
    );

    CompletableFuture<List<InventoryItem>> findAll(
            final String inventoryId
    );
}
