package com.github.psinalberth.domain.product.infrastructure.database;

import com.github.psinalberth.domain.product.core.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jsr330")
public interface ProductDatabaseMapper {

    @Mapping(target = "id", expression = "java(toProductId(code, product.id()))")
    ProductMongoEntity toEntity(final String code, final Product product);

    default ProductId toProductId(final String inventoryId, final String productId) {
        return new ProductId(inventoryId, productId);
    }

    @Mapping(target = "id", source = "id.barCode")
    Product toDomain(final ProductMongoEntity entity);
}
