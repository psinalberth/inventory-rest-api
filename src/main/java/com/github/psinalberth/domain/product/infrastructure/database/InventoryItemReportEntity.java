package com.github.psinalberth.domain.product.infrastructure.database;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

public record InventoryItemReportEntity(

        @Field("product_id")
        String productId,

        String name,

        BigDecimal price,

        @Field(value = "expected_quantity", targetType = FieldType.DOUBLE)
        BigDecimal expectedQuantity,

        @Field(value = "expected_total_price", targetType = FieldType.DOUBLE)
        BigDecimal expectedTotalPrice,

        @Field(value = "actual_quantity", targetType = FieldType.DOUBLE)
        BigDecimal actualQuantity,

        @Field(value = "actual_total_price", targetType = FieldType.DOUBLE)
        BigDecimal actualTotalPrice
) {
}
