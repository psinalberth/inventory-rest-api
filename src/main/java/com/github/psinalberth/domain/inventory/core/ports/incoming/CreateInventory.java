package com.github.psinalberth.domain.inventory.core.ports.incoming;

import com.github.psinalberth.domain.inventory.core.model.Inventory;
import com.github.psinalberth.domain.inventory.core.model.command.CreateInventoryCommand;
import com.github.psinalberth.domain.shared.model.Result;

import java.util.concurrent.CompletableFuture;

public interface CreateInventory {

    CompletableFuture<Result<Inventory>> create(final CreateInventoryCommand command);
}
