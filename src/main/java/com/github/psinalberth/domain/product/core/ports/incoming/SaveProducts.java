package com.github.psinalberth.domain.product.core.ports.incoming;

import com.github.psinalberth.domain.inventory.core.model.command.SaveManyProductCommand;
import com.github.psinalberth.domain.product.core.model.Product;
import com.github.psinalberth.domain.shared.model.Result;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SaveProducts {

    CompletableFuture<Result<List<Product>>> saveMany(final SaveManyProductCommand command);
}
