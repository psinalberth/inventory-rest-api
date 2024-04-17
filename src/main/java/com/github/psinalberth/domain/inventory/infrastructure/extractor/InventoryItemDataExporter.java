package com.github.psinalberth.domain.inventory.infrastructure.extractor;

import com.github.psinalberth.domain.inventory.core.model.InventoryItemReport;
import com.github.psinalberth.domain.shared.port.outgoing.DataExporter;

public interface InventoryItemDataExporter extends DataExporter<InventoryItemOpenCsv, InventoryItemReport> {
}
