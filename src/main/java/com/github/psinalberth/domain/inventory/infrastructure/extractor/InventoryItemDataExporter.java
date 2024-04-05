package com.github.psinalberth.domain.inventory.infrastructure.extractor;

import com.github.psinalberth.domain.product.infrastructure.database.InventoryItemReportEntity;
import com.github.psinalberth.domain.shared.port.outgoing.DataExporter;

public interface InventoryItemDataExporter extends DataExporter<InventoryItemOpenCsv, InventoryItemReportEntity> {
}
