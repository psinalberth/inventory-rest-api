package com.github.psinalberth.domain.inventory.core.ports.incoming;

import com.github.psinalberth.domain.inventory.core.model.InventoryItemReport;
import com.github.psinalberth.domain.shared.model.Result;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ExportInventoryItem {

    CompletableFuture<Result<List<InventoryItemReport>>> query(final String inventoryId);
}
