package com.github.psinalberth.domain.product.infrastructure.database;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Named
@RequiredArgsConstructor
public class BatchTypeMongoRepository  {

    private final ReactiveMongoTemplate mongoTemplate;

    public Mono<BatchTypeMongoEntity> findById(String inventoryId, String id) {
        var aggregation = newAggregation(
                match(where("code").is(inventoryId)),
                unwind("$batch_types"),
                project()
                        .and("$batch_types._id").as("_id")
                        .and("$batch_types.name").as("name"),
                match(where("_id").is(id))
        );

        return mongoTemplate.aggregate(aggregation, "inventories", BatchTypeMongoEntity.class)
                .next();
    }
}
