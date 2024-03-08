package com.github.psinalberth.domain.inventory.application.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItemDto {

    private String productId;
    private String name;
    private BatchTypeDto batchType;
    private BigDecimal quantity;
    private BigDecimal price;

    public BigDecimal getTotalPrice() {
        if (Objects.isNull(quantity) || Objects.isNull(price))
            return BigDecimal.ZERO;

        return quantity.multiply(price);
    }
}
