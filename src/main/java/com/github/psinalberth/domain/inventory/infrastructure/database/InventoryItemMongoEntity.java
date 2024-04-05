package com.github.psinalberth.domain.inventory.infrastructure.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "inventory_items")
public record InventoryItemMongoEntity(

        @Id
        @Field(name = "_id")
        InventoryItemId id,

        @Field(name = "product_id")
        String productId,

        @Field(name = "batch_type_id")
        String batchTypeId,

        @Field(name = "inventory_id")
        String inventoryId,

        @Field(name = "group_id")
        Long groupId,

        @Field(name = "quantity", targetType = FieldType.DECIMAL128)
        BigDecimal quantity,

        @Field(name = "created_at")
        LocalDateTime createdAt,

        @Field(name = "updated_at")
        LocalDateTime updatedAt
) {
}
