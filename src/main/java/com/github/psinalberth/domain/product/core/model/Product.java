package com.github.psinalberth.domain.product.core.model;

import com.github.psinalberth.domain.shared.model.Identity;

import java.math.BigDecimal;

public record Product(
        String id,
        String name,
        ProductCategory category,
        ProductDepartment department,
        ProductGroup group,
        BigDecimal quantity,
        BigDecimal price
) implements Identity, IProduct {

    public Long getGroupId() {
        if (group == null)
            return null;

        return group.code();
    }
}
