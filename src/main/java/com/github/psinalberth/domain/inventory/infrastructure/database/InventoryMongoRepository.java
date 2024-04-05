package com.github.psinalberth.domain.inventory.infrastructure.database;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface InventoryMongoRepository extends ReactiveMongoRepository<InventoryMongoEntity, String> {

    Mono<InventoryMongoEntity> findByCode(final String code);
}
