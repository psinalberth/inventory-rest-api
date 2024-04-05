package com.github.psinalberth.domain.inventory.core.ports.outgoing;

import com.github.psinalberth.domain.inventory.core.model.Inventory;

import java.util.concurrent.CompletableFuture;

public interface InventoryRepository {

    CompletableFuture<Inventory> save(final Inventory inventory);

    CompletableFuture<Inventory> update(final Inventory inventory);

    CompletableFuture<Inventory> fetch(final String id);
}
