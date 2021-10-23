package com.github.psinalberth.domain.inventory.usecase;

import com.github.psinalberth.domain.inventory.dto.output.InventoryDto;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public interface CreateInventoryUseCase {

    InventoryDto create(CreateInventoryCommand command);

    @Value
    class CreateInventoryCommand {

        @NotEmpty(message = "Company is required.")
        String company;

        @NotEmpty(message = "Subsidiary is required.")
        String subsidiary;

        @NotEmpty(message = "Title is required.")
        String title;

        @Size(min = 1, message = "At least one batch type is required.")
        List<String> batchTypes;
    }
}