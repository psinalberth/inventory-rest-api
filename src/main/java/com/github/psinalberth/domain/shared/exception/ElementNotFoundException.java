package com.github.psinalberth.domain.shared.exception;

public class ElementNotFoundException extends BusinessException {

    public ElementNotFoundException(String message) {
        super(message);
    }

    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
