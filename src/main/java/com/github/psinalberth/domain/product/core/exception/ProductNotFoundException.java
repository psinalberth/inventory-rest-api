package com.github.psinalberth.domain.product.core.exception;

import com.github.psinalberth.domain.shared.exception.ResourceNotFoundException;

public class ProductNotFoundException extends ResourceNotFoundException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
