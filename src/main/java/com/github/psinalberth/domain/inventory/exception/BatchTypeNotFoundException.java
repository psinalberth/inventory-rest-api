package com.github.psinalberth.domain.inventory.exception;

import com.github.psinalberth.domain.shared.exception.ElementNotFoundException;

public class BatchTypeNotFoundException extends ElementNotFoundException {

    public BatchTypeNotFoundException(String message) {
        super(message);
    }

    public BatchTypeNotFoundException(Long batchTypeId) {
        this("Batch type not found. ID = " + batchTypeId);
    }
}
