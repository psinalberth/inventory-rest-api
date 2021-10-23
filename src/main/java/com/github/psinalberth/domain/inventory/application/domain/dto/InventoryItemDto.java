package com.github.psinalberth.domain.inventory.application.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItemDto {

    private String productId;
    private BatchTypeDto batchType;
    private BigDecimal quantity;
}
