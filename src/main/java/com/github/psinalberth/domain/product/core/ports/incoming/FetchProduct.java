package com.github.psinalberth.domain.product.core.ports.incoming;

import com.github.psinalberth.domain.product.core.model.Product;
import com.github.psinalberth.domain.product.core.model.command.FetchProductCommand;
import com.github.psinalberth.domain.shared.model.Result;

import java.util.concurrent.CompletableFuture;

public interface FetchProduct {

    CompletableFuture<Result<Product>> fetch(final FetchProductCommand command);
}
