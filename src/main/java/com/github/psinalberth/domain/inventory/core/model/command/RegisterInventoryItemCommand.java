package com.github.psinalberth.domain.inventory.core.model.command;

import java.math.BigDecimal;

public record RegisterInventoryItemCommand(
        String inventoryId,

        String productId,

        String batchTypeId,

        BigDecimal quantity
) {

    public RegisterInventoryItemCommand withInventoryId(final String inventoryId) {
        return new RegisterInventoryItemCommand(
                inventoryId,
                productId(),
                batchTypeId(),
                quantity()
        );
    }
}
