package com.github.psinalberth.domain.inventory.core.ports.incoming;

import com.github.psinalberth.domain.inventory.core.model.InventoryItem;
import com.github.psinalberth.domain.inventory.core.model.command.RegisterInventoryItemCommand;
import com.github.psinalberth.domain.shared.model.Result;

import java.util.concurrent.CompletableFuture;

public interface RegisterInventoryItem {

    CompletableFuture<Result<InventoryItem>> register(final RegisterInventoryItemCommand command);
}
