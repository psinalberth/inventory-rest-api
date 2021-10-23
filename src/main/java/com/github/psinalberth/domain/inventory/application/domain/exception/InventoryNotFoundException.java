package com.github.psinalberth.domain.inventory.application.domain.exception;

import com.github.psinalberth.domain.shared.exception.ElementNotFoundException;

public class InventoryNotFoundException extends ElementNotFoundException {

    public InventoryNotFoundException(String message) {
        super(message);
    }

    public InventoryNotFoundException(CharSequence code) {
        this("Inventory not found. ID = " + code);
    }
}
