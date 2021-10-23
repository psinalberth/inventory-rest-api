package com.github.psinalberth.domain.user.service;

import lombok.Value;

import javax.validation.constraints.NotEmpty;

public interface RetrieveUserUseCase {

    ApplicationUser retrieve(RetrieveUserCommand command);

    @Value
    class RetrieveUserCommand {

        @NotEmpty(message = "User id is required")
        String userId;
    }
}
