package com.github.psinalberth.domain.inventory.infrastructure.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "inventories")
public record InventoryMongoEntity(
        @Id
        @Field(value = "_id", targetType = FieldType.STRING)
        String id,
        @Field("code")
        String code,
        @Field("title")
        String title,
        @Field("batch_types")
        List<BatchTypeEntity> batchTypes,
        @Field("groups")
        List<InventoryGroupEntity> groups,
        @Field("created_at")
        LocalDateTime createdAt,
        @Field("updated_at")
        LocalDateTime updatedAt
) {
}
