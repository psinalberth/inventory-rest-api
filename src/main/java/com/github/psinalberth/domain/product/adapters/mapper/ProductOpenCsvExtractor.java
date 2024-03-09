package com.github.psinalberth.domain.product.adapters.mapper;

import com.github.psinalberth.domain.product.application.domain.model.Product;
import com.github.psinalberth.domain.product.application.domain.model.ProductCategory;
import com.github.psinalberth.domain.product.application.domain.model.ProductDepartment;
import com.github.psinalberth.domain.product.application.domain.model.ProductGroup;
import com.github.psinalberth.infrastructure.shared.OpenCsvExtractor;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductOpenCsvExtractor extends OpenCsvExtractor implements ProductCsvExtractor {

    @Override
    public List<Product> extract(final InputStream inputStream) {
        return this.extract(ProductOpenCsvDto.class, inputStream)
                .stream()
                .map(this::createProduct)
                .collect(Collectors.toList());
    }

    private Product createProduct(final ProductOpenCsvDto dto) {
        return Optional.ofNullable(dto)
                .map(v -> new Product(
                        v.getProductId(),
                        v.getName(),
                        new ProductCategory(v.getCategory().getCategoryId(), v.getCategory().getName(), null, null),
                        new ProductGroup(v.getGroup().getGroupId(), v.getGroup().getName(), null, null),
                        new ProductDepartment(v.getDepartment().getDepartmentId(), v.getDepartment().getName(), null),
                        v.getPrice(),
                        v.getQuantity()
                ))
                .orElse(null);
    }
}
