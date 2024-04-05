package com.github.psinalberth.domain.product.core.model.command;

public record FetchProductCommand(
        String inventoryId,

        String productId
) {
}
