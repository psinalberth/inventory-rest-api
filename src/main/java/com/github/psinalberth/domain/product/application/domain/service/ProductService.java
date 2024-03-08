package com.github.psinalberth.domain.product.application.domain.service;

import com.github.psinalberth.domain.product.adapters.mapper.ProductCsvExtractor;
import com.github.psinalberth.domain.product.adapters.mapper.ProductMapper;
import com.github.psinalberth.domain.product.application.domain.dto.ProductDto;
import com.github.psinalberth.domain.product.application.domain.port.incoming.CreateProductUseCase;
import com.github.psinalberth.domain.product.application.domain.port.incoming.ImportProductUseCase;
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
public class ProductService implements CreateProductUseCase, QueryProductUseCase, ImportProductUseCase {

    private final SaveProductPort saveProductPort;
    private final LoadProductPort loadProductPort;
    private final ProductMapper productMapper;
    private final ProductCsvExtractor productCsvExtractor;

    @Override
    @Transactional
    public ProductDto create(CreateProductCommand command) {

        var product = productMapper.toEntity(command);

        Optional.ofNullable(product.getCategory())
                .ifPresent(category -> category.setProduct(product));

        Optional.ofNullable(product.getDepartment())
                .ifPresent(department -> department.setProduct(product));

        Optional.ofNullable(product.getGroup())
                .ifPresent(group -> group.setProduct(product));

        return productMapper.toOutputModel(saveProductPort.save(product));
    }

    @Override
    public ProductDto query(QueryProductCommand command) {
        return loadProductPort.findByProductId(command.getProductId())
                .map(productMapper::toOutputModel)
                .orElseThrow(() -> new ElementNotFoundException("It was not possible to find product with id " + command.getProductId()));
    }

    @Override
    @Transactional
    public void doImport(ImportProductCommand command) {
        productCsvExtractor.extract(command.getProductBase())
                .forEach(p -> {

                    Optional.ofNullable(p.getCategory())
                            .ifPresent(category -> category.setProduct(p));

                    Optional.ofNullable(p.getDepartment())
                            .ifPresent(department -> department.setProduct(p));

                    Optional.ofNullable(p.getGroup())
                            .ifPresent(group -> group.setProduct(p));

                    saveProductPort.save(p);
                });
    }
}
