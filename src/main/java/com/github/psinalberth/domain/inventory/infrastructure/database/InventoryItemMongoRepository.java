package com.github.psinalberth.domain.inventory.infrastructure.database;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface InventoryItemMongoRepository extends ReactiveMongoRepository<InventoryItemMongoEntity, InventoryItemId> {

    Flux<InventoryItemMongoEntity> findAllByInventoryId(final String inventoryId);
}
