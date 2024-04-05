package com.github.psinalberth.domain.inventory.core.model;

import com.github.psinalberth.domain.shared.model.Identity;

import java.time.LocalDateTime;
import java.util.List;

public record Inventory (
        String id,
        String code,
        String title,
        List<BatchType> batchTypes,
        List<InventoryGroup> groups,
        LocalDateTime createdAt
) implements Identity {

    public Inventory withCode(final String code) {
        return new Inventory(
                id(),
                code,
                title(),
                batchTypes(),
                groups(),
                createdAt()
        );
    }

    public Inventory withGroups(final List<InventoryGroup> groups) {
        return new Inventory(
                id(),
                code(),
                title(),
                batchTypes(),
                groups,
                createdAt()
        );
    }
}
