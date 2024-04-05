package com.github.psinalberth.api.providers.reactor;

import com.github.psinalberth.domain.shared.exception.BusinessException;
import com.github.psinalberth.domain.shared.exception.ResourceNotFoundException;
import com.github.psinalberth.domain.shared.exception.ValidationException;
import org.springframework.http.HttpStatus;

public class ServerResponseStatusMapper {

    public static HttpStatus mapExceptionToStatus(final Throwable exception) {
        return switch (exception) {
            case ValidationException ignored -> HttpStatus.BAD_REQUEST;
            case ResourceNotFoundException ignored -> HttpStatus.NOT_FOUND;
            case BusinessException ignored -> HttpStatus.PRECONDITION_FAILED;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
