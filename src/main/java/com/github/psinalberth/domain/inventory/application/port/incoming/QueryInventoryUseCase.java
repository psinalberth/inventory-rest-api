package com.github.psinalberth.domain.inventory.application.port.incoming;

import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryDto;

import jakarta.validation.constraints.NotEmpty;

public interface QueryInventoryUseCase {

    InventoryDto query(QueryInventoryCommand command);

    record QueryInventoryCommand(
            @NotEmpty(message = "Inventory code is required.")
            String code
    ) {
    }
}
