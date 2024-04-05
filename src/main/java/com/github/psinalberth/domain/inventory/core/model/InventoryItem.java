package com.github.psinalberth.domain.inventory.core.model;

import com.github.psinalberth.domain.product.core.model.Product;
import com.github.psinalberth.domain.shared.model.Identity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InventoryItem(
        String productId,
        String batchTypeId,
        String inventoryId,
        Long groupId,
        BigDecimal quantity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) implements Identity {

    public InventoryItem withProduct(final Product product) {
        return new InventoryItem(
                product.id(),
                batchTypeId(),
                inventoryId(),
                product.getGroupId(),
                quantity(),
                createdAt(),
                updatedAt()
        );
    }

    public InventoryItem withBatchType(final String batchTypeId) {
        return new InventoryItem(
                productId(),
                batchTypeId,
                inventoryId(),
                groupId(),
                quantity(),
                createdAt(),
                updatedAt()
        );
    }

    public InventoryItem withQuantity(final BigDecimal quantity) {
        return new InventoryItem(
                productId(),
                batchTypeId(),
                inventoryId(),
                groupId(),
                quantity,
                createdAt(),
                LocalDateTime.now()
        );
    }

    @Override
    public String id() {
        return productId;
    }
}
