package com.github.psinalberth.domain.inventory.core.ports.outgoing;

import com.github.psinalberth.domain.inventory.core.model.InventoryItemReport;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface InventoryItemReportRepository {

    CompletableFuture<List<InventoryItemReport>> queryItems(final String inventoryId);
}
