package com.github.psinalberth.domain.product.application.domain.service;

import com.github.psinalberth.domain.product.adapters.mapper.ProductMapper;
import com.github.psinalberth.domain.product.application.domain.dto.ProductDto;
import com.github.psinalberth.domain.product.application.domain.model.Product;
import com.github.psinalberth.domain.product.application.domain.port.incoming.CreateProductUseCase;
import com.github.psinalberth.domain.product.application.domain.port.incoming.QueryProductUseCase;
import com.github.psinalberth.domain.product.application.domain.port.outgoing.LoadProductPort;
import com.github.psinalberth.domain.product.application.domain.port.outgoing.SaveProductPort;
import com.github.psinalberth.domain.shared.domain.annotation.UseCase;
import com.github.psinalberth.domain.shared.domain.exception.ElementNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class ProductService implements CreateProductUseCase, QueryProductUseCase {

    private final SaveProductPort saveProductPort;
    private final LoadProductPort loadProductPort;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductDto create(CreateProductCommand command) {

        Product product = productMapper.toEntity(command);

        product.getCategories().forEach(category -> category.setProduct(product));
        product.getGroups().forEach(group -> group.setProduct(product));

        return productMapper.toOutputModel(saveProductPort.save(product));
    }

    @Override
    public ProductDto query(QueryProductCommand command) {
        return loadProductPort.findByProductId(command.getProductId())
                .map(productMapper::toOutputModel)
                .orElseThrow(() -> new ElementNotFoundException("It was not possible to find product with id " + command.getProductId()));
    }
}
