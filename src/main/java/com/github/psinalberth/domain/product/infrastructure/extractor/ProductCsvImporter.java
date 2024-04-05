package com.github.psinalberth.domain.product.infrastructure.extractor;

import com.github.psinalberth.api.providers.opencsv.CsvImporter;
import com.github.psinalberth.domain.product.core.model.Product;
import com.github.psinalberth.domain.product.core.model.ProductCategory;
import com.github.psinalberth.domain.product.core.model.ProductDepartment;
import com.github.psinalberth.domain.product.core.model.ProductGroup;
import jakarta.inject.Named;

import java.util.Optional;

@Named
class ProductCsvImporter extends CsvImporter<Product, ProductOpenCsv> implements ProductDataImporter {

    @Override
    public Product mapToBean(final ProductOpenCsv input) {
        return Optional.ofNullable(input)
                .map(value -> new Product(
                        value.id(),
                        value.name(),
                        toCategory(input.category()),
                        toDepartment(input.department()),
                        toGroup(input.group()),
                        value.quantity(),
                        value.price()
                ))
                .orElse(null);
    }

    private static ProductCategory toCategory(final ProductCategoryOpenCsv input) {
        return Optional.ofNullable(input)
                .map(value -> new ProductCategory(
                        input.code(),
                        input.name()
                ))
                .orElse(null);
    }

    private static ProductGroup toGroup(final ProductGroupOpenCsv input) {
        return Optional.ofNullable(input)
                .map(value -> new ProductGroup(
                        input.code(),
                        input.name()
                ))
                .orElse(null);
    }

    private static ProductDepartment toDepartment(final ProductDepartmentOpenCsv input) {
        return Optional.ofNullable(input)
                .map(value -> new ProductDepartment(
                        input.code(),
                        input.name()
                ))
                .orElse(null);
    }

    @Override
    public Class<ProductOpenCsv> getTarget() {
        return ProductOpenCsv.class;
    }
}
