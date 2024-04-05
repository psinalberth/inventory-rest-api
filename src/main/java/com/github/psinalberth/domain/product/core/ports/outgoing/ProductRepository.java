package com.github.psinalberth.domain.product.core.ports.outgoing;

import com.github.psinalberth.domain.product.core.model.Product;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public interface ProductRepository {

    CompletableFuture<Product> save(final Product product);

    CompletableFuture<Product> findById(final String inventoryId, final String productId);

    CompletableFuture<List<Product>> saveAll(final String inventoryId, final Stream<Product> products);
}
