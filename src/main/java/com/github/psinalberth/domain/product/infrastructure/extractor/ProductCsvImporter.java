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
                        toCategory(value.category()),
                        toDepartment(value.department()),
                        toGroup(value.group()),
                        value.measureUnit(),
                        value.quantity(),
                        value.price()
                ))
                .orElse(null);
    }

    private static ProductCategory toCategory(final ProductCategoryOpenCsv input) {
        return Optional.ofNullable(input)
                .map(value -> new ProductCategory(
                        value.code(),
                        value.name()
                ))
                .orElse(null);
    }

    private static ProductGroup toGroup(final ProductGroupOpenCsv input) {
        return Optional.ofNullable(input)
                .map(value -> new ProductGroup(
                        value.code(),
                        value.name()
                ))
                .orElse(null);
    }

    private static ProductDepartment toDepartment(final ProductDepartmentOpenCsv input) {
        return Optional.ofNullable(input)
                .map(value -> new ProductDepartment(
                        value.code(),
                        value.name()
                ))
                .orElse(null);
    }

    @Override
    public Class<ProductOpenCsv> getTarget() {
        return ProductOpenCsv.class;
    }
}
