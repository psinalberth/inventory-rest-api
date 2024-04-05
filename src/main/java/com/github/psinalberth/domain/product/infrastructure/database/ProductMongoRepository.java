package com.github.psinalberth.domain.product.infrastructure.database;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMongoRepository extends ReactiveMongoRepository<ProductMongoEntity, ProductId> {
}
