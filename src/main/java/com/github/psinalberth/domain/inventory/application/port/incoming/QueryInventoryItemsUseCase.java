package com.github.psinalberth.domain.inventory.application.port.incoming;

import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryItemDto;

import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryReportItemDto;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public interface QueryInventoryItemsUseCase {

    List<InventoryItemDto> query(QueryInventoryItemsCommand command);

    List<InventoryReportItemDto> queryReport(QueryInventoryItemsCommand command);

    record QueryInventoryItemsCommand(
            @NotEmpty(message = "Inventory code is required.")
            String code
    ) {
    }
}
