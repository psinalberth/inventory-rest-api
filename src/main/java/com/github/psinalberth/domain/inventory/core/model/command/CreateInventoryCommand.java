package com.github.psinalberth.domain.inventory.core.model.command;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateInventoryCommand(

        @NotEmpty(message = "Company is required.")
        String company,
        String subsidiary,
        String title,
        List<String> batchTypes
) {
}
