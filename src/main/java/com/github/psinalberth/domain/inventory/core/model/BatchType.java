package com.github.psinalberth.domain.inventory.core.model;

import com.github.psinalberth.domain.shared.model.Identity;

public record BatchType(
        String id,
        String name
) implements Identity {
}
