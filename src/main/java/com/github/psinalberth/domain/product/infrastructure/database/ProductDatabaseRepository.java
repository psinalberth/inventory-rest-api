package com.github.psinalberth.domain.product.infrastructure.database;

import com.github.psinalberth.domain.product.core.model.Product;
import com.github.psinalberth.domain.product.core.ports.outgoing.ProductRepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Slf4j
@Named
@RequiredArgsConstructor
public class ProductDatabaseRepository implements ProductRepository {

    private final ProductMongoRepository delegate;
    private final ProductDatabaseMapper mapper;

    @Override
    public CompletableFuture<Product> save(final Product product) {
        log.info("Saving product {}", product);
        return Mono.just(mapper.toEntity(null, product))
                .flatMap(delegate::save)
                .map(mapper::toDomain)
                .toFuture();
    }

    @Override
    public CompletableFuture<Product> findById(final String inventoryId, final String productId) {
        log.info("Fetching product with id {}", productId);
        return delegate.findById(new ProductId(inventoryId, productId))
                .map(mapper::toDomain)
                .toFuture();
    }

    @Override
    public CompletableFuture<List<Product>> saveAll(final String inventoryId, final Stream<Product> products) {
        log.info("Saving products related to inventory with id {}", inventoryId);
        return Flux.fromStream(products)
                .map(product -> mapper.toEntity(inventoryId, product))
                .flatMap(delegate::save)
                .map(mapper::toDomain)
                .collectList()
                .toFuture();

    }
}
