package com.github.psinalberth.domain.inventory.core.ports.incoming;

import com.github.psinalberth.domain.inventory.core.model.InventoryItem;
import com.github.psinalberth.domain.inventory.core.model.command.ListInventoryItemCommand;
import com.github.psinalberth.domain.shared.model.Result;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ListItems {

    CompletableFuture<Result<List<InventoryItem>>> findAll(final ListInventoryItemCommand command);
}
