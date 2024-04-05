package com.github.psinalberth.domain.product.infrastructure.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Document(collection = "products")
public record ProductMongoEntity(

        @Id
        @Field(value = "_id")
        ProductId id,

        @Field("name")
        String name,

        @Field("category")
        ProductCategoryEntity category,

        @Field("department")
        ProductDepartmentEntity department,

        @Field("group")
        ProductGroupEntity group,

        @Field(value = "quantity", targetType = FieldType.DOUBLE)
        BigDecimal quantity,

        @Field(value = "price", targetType = FieldType.DOUBLE)
        BigDecimal price
) {
}
