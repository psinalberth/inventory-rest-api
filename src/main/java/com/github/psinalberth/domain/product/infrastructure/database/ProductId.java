package com.github.psinalberth.domain.product.infrastructure.database;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
public record ProductId(

        @Field("inventory_id")
        String inventoryId,

        @Field("code")
        String barCode
) implements Serializable {
}
