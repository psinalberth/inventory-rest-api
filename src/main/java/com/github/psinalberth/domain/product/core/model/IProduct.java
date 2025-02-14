package com.github.psinalberth.domain.product.core.model;

import java.math.BigDecimal;

public interface IProduct {
    String id();
    String name();
    IProductCategory category();
    IProductDepartment department();
    IProductGroup group();
    String measureUnit();
    BigDecimal quantity();
    BigDecimal price();
}
