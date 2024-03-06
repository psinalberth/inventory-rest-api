package com.github.psinalberth.domain.inventory.application.port.incoming;

import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

public interface CreateInventoryUseCase {

    InventoryDto create(CreateInventoryCommand command);

    record CreateInventoryCommand(
            @NotEmpty(message = "Company is required.")
            String company,
            @NotEmpty(message = "Subsidiary is required.")
            String subsidiary,
            @NotEmpty(message = "Title is required.")
            String title,
            @Size(min = 1, message = "At least one batch type is required.")
            List<String> batchTypes
    ) {
    }
}