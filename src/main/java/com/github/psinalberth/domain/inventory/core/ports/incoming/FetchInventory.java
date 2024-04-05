package com.github.psinalberth.domain.inventory.core.ports.incoming;

import com.github.psinalberth.domain.inventory.core.model.Inventory;
import com.github.psinalberth.domain.inventory.core.model.command.FetchInventoryCommand;
import com.github.psinalberth.domain.shared.model.Result;

import java.util.concurrent.CompletableFuture;

public interface FetchInventory {

    CompletableFuture<Result<Inventory>> fetch(final FetchInventoryCommand command);
}
