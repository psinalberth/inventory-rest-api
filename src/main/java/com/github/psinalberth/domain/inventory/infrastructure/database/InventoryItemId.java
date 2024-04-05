package com.github.psinalberth.domain.inventory.infrastructure.database;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

public record InventoryItemId(

        @Field(name = "inventory_id")
        String inventoryId,

        @Field(name = "product_id")
        String productId,

        @Field(name = "batch_type_id")
        String batchTypeId

) implements Serializable {
}
