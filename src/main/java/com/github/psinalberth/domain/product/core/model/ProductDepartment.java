package com.github.psinalberth.domain.product.core.model;

public record ProductDepartment(
        Long code,
        String name
) implements IProductDepartment {
}
