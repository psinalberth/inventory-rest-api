package com.github.psinalberth.domain.product.application.domain.model;

public interface IProduct {
    String getProductId();

    String getName();

    IProductCategory getCategory();

    IProductGroup getGroup();

    IProductDepartment getDepartment();
}
