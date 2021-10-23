package com.github.psinalberth.domain.inventory.usecase;

import com.github.psinalberth.domain.inventory.dto.output.InventoryDto;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

public interface QueryInventoryUseCase {

    InventoryDto query(QueryInventoryCommand command);

    @Value
    class QueryInventoryCommand {

        @NotEmpty(message = "Inventory code is required.")
        String code;
    }
}
