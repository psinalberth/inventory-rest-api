package com.github.psinalberth.domain.product.infrastructure.database;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Multiply;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import reactor.core.publisher.Flux;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.BooleanOperators.And.and;
import static org.springframework.data.mongodb.core.aggregation.ComparisonOperators.Eq;
import static org.springframework.data.mongodb.core.aggregation.LookupOperation.newLookup;
import static org.springframework.data.mongodb.core.aggregation.VariableOperators.Let.ExpressionVariable.newVariable;
import static org.springframework.data.mongodb.core.aggregation.VariableOperators.Let.just;
import static org.springframework.data.mongodb.core.query.Criteria.expr;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Named
@RequiredArgsConstructor
public class InventoryItemQuery {

    private final ReactiveMongoTemplate mongoTemplate;

    public Flux<InventoryItemReportEntity> queryItems(final String inventoryId) {
        var aggregation = newAggregation(
                ProductMongoEntity.class,
                match(where("_id.inventory_id").is(inventoryId)),
                lookupOperation(),
                unwind("$products"),
                projection()
        );

        return mongoTemplate.aggregate(aggregation, "inventory_items", InventoryItemReportEntity.class);
    }

    private static ProjectionOperation projection() {
        return project()
                .andInclude("product_id")
                .and("$products.name").as("name")
                .and("$products.price").as("price")
                .and("$products.quantity").as("expected_quantity")
                .and("$quantity").as("actual_quantity")
                .and(Multiply.valueOf("$products.price")
                        .multiplyBy("$products.quantity"))
                .as("expected_total_price")
                .and(Multiply.valueOf("$products.price")
                        .multiplyBy("$quantity"))
                .as("actual_total_price");
    }

    private static LookupOperation lookupOperation() {
        return newLookup()
                .from("products")
                .let(just(
                        newVariable("inventory_id").forField("$inventory_id"),
                        newVariable("product_id").forField("$product_id")
                ))
                .pipeline(
                        match(
                                expr(

                                        and(
                                                Eq.valueOf("$_id.code").equalTo("$$product_id"),
                                                Eq.valueOf("$_id.inventory_id").equalTo("$$inventory_id")
                                        )
                                )
                        )
                )
                .as("products");
    }
}
