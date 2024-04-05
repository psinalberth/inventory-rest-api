package com.github.psinalberth.domain.product.core.model;

public record ProductGroup(
        Long code,
        String name
) implements IProductGroup {
}
