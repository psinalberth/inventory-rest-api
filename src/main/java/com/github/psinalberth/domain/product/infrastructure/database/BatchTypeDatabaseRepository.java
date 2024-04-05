package com.github.psinalberth.domain.product.infrastructure.database;

import com.github.psinalberth.domain.inventory.core.model.BatchType;
import com.github.psinalberth.domain.product.core.ports.outgoing.BatchTypeRepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Named
@RequiredArgsConstructor
public class BatchTypeDatabaseRepository implements BatchTypeRepository {

    private final BatchTypeMongoRepository delegate;

    @Override
    public CompletableFuture<BatchType> findById(String inventoryId, String id) {
        return delegate.findById(inventoryId, id)
                .map(entity -> new BatchType(entity.id(), entity.name()))
                .toFuture();
    }
}
