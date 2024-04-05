package com.github.psinalberth.domain.product.infrastructure.database;

import org.springframework.data.mongodb.core.mapping.Field;

public record ProductDepartmentEntity(
        @Field("code")
        Long code,

        @Field("name")
        String name
) {
}
