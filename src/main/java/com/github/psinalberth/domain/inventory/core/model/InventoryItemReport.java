package com.github.psinalberth.domain.inventory.core.model;

import java.math.BigDecimal;

public record InventoryItemReport(

        String productId,

        String name,

        BigDecimal price,

        BigDecimal expectedQuantity,

        BigDecimal actualQuantity,

        BigDecimal expectedTotalPrice,

        BigDecimal actualTotalPrice
) {
}
