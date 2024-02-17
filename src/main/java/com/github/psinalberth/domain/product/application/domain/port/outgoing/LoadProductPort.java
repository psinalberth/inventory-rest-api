package com.github.psinalberth.domain.product.application.domain.port.outgoing;

import com.github.psinalberth.domain.product.application.domain.model.Product;

import java.util.Optional;

public interface LoadProductPort {

    Optional<Product> findByProductId(String productId);
}
