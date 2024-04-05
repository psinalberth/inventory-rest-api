package com.github.psinalberth.domain.product.core.ports.outgoing;

import com.github.psinalberth.domain.inventory.core.model.BatchType;

import java.util.concurrent.CompletableFuture;

public interface BatchTypeRepository {

    CompletableFuture<BatchType> findById(final String inventoryId, final String id);
}
