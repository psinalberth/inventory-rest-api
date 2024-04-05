package com.github.psinalberth.domain.product.core;

import com.github.psinalberth.domain.inventory.core.model.command.SaveManyProductCommand;
import com.github.psinalberth.domain.product.core.exception.ProductNotFoundException;
import com.github.psinalberth.domain.product.core.model.Product;
import com.github.psinalberth.domain.product.core.model.command.FetchProductCommand;
import com.github.psinalberth.domain.product.core.ports.incoming.FetchProduct;
import com.github.psinalberth.domain.product.core.ports.incoming.SaveProducts;
import com.github.psinalberth.domain.product.core.ports.outgoing.ProductRepository;
import com.github.psinalberth.domain.product.infrastructure.extractor.ProductDataImporter;
import com.github.psinalberth.domain.shared.model.Result;
import com.github.psinalberth.domain.shared.port.outgoing.ConfigProvider;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Named
@RequiredArgsConstructor
public class ProductService implements SaveProducts, FetchProduct {

    private final ProductDataImporter productDataExtractor;
    private final ProductRepository productRepository;
    private final ConfigProvider configProvider;

    @Override
    public CompletableFuture<Result<List<Product>>> saveMany(final SaveManyProductCommand command) {
        return Result.fromSupplier(() -> productDataExtractor.doImport(command.productBase()))
                .flatMap(products ->  Result.fromFuture(() -> productRepository.saveAll(command.inventoryId(), products)))
                .toFuture();
    }

    @Override
    public CompletableFuture<Result<Product>> fetch(FetchProductCommand command) {
        return Result.fromFuture(() -> productRepository.findById(command.inventoryId(), command.productId()))
                .onEmptyTransform(() -> productRepository.findById(configProvider.getDefaultInventoryId(), command.productId()))
                .onEmptyFailWith(() -> new ProductNotFoundException("Product not found with id " + command.productId()))
                .toFuture();
    }
}
