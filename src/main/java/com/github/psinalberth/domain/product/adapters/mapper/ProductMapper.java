package com.github.psinalberth.domain.product.adapters.mapper;

import com.github.psinalberth.domain.product.application.domain.dto.ProductDto;
import com.github.psinalberth.domain.product.application.domain.model.Product;
import com.github.psinalberth.domain.product.application.domain.model.ProductCategory;
import com.github.psinalberth.domain.product.application.domain.model.ProductDepartment;
import com.github.psinalberth.domain.product.application.domain.model.ProductGroup;
import com.github.psinalberth.domain.product.application.domain.port.incoming.CreateProductUseCase;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {


    @Mapping(target = "productId", source = "barCode")
    Product toEntity(CreateProductUseCase.CreateProductCommand command);

    @Mapping(target = "productId", source = "productId")
    Product toEntity(String productId);

    ProductDto toOutputModel(Product product);

    @Mapping(target = "name", source = "category")
    ProductCategory toProductCategoryEntity(String category);

    @Mapping(target = "name", source = "group")
    ProductGroup toProductGroupEntity(String group);

    @Mapping(target = "name", source = "department")
    ProductDepartment toProductDepartmentEntity(String department);

    default String toProductCategoryOutputModel(ProductCategory productCategory) {
        return Optional.ofNullable(productCategory)
                .map(value -> String.valueOf(value.getName()))
                .orElse(null);
    }

    default String toProductGroupOutputModel(ProductGroup productGroup) {
        return Optional.ofNullable(productGroup)
                .map(value -> String.valueOf(value.getName()))
                .orElse(null);
    }

    default String toProductDepartmentOutputModel(ProductDepartment productDepartment) {
        return Optional.ofNullable(productDepartment)
                .map(value -> String.valueOf(value.getName()))
                .orElse(null);
    }
}
