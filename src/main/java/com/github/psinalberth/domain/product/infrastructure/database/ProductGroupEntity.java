package com.github.psinalberth.domain.product.infrastructure.database;

import org.springframework.data.mongodb.core.mapping.Field;

public record ProductGroupEntity(

        @Field("code")
        Long code,

        @Field("name")
        String name
) {
}
