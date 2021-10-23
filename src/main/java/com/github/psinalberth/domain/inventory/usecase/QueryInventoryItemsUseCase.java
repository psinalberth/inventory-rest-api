package com.github.psinalberth.domain.inventory.usecase;

import com.github.psinalberth.domain.inventory.dto.output.InventoryItemDto;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public interface QueryInventoryItemsUseCase {

    List<InventoryItemDto> query(QueryInventoryItemsCommand command);

    @Value
    class QueryInventoryItemsCommand {

        @NotEmpty(message = "Inventory code is required.")
        String code;
    }
}
