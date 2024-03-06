package com.github.psinalberth.domain.product.adapters.mapper;

import com.github.psinalberth.domain.product.application.domain.model.Product;

import java.io.InputStream;
import java.util.List;

public interface ProductCsvExtractor {

    List<Product> extract(InputStream inputStream);
}
