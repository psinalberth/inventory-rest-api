package com.github.psinalberth.domain.inventory.core.model.command;

import java.io.InputStream;

public record SaveManyProductCommand(
        String inventoryId,

        InputStream productBase
) {
}
