package com.github.psinalberth.domain.inventory.infrastructure.extractor;

import com.opencsv.bean.CsvBindByPosition;

import java.math.BigDecimal;

public record InventoryItemOpenCsv(

        @CsvBindByPosition(position = 0)
        String productId,

        @CsvBindByPosition(position = 1)
        String name,

        @CsvBindByPosition(position = 2)
        BigDecimal price,

        @CsvBindByPosition(position = 3)
        BigDecimal expectedQuantity,

        @CsvBindByPosition(position = 4)
        BigDecimal expectedTotalPrice,

        @CsvBindByPosition(position = 5)
        BigDecimal actualQuantity,

        @CsvBindByPosition(position = 6)
        BigDecimal actualTotalPrice
) {
}
