package com.github.psinalberth.domain.product.core.model;

public record ProductCategory(
        Long code,
        String name
) implements IProductCategory {
}
