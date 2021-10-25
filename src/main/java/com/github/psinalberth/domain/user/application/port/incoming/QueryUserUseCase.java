package com.github.psinalberth.domain.user.application.port.incoming;

import com.github.psinalberth.domain.user.application.domain.model.ApplicationUser;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

public interface QueryUserUseCase {

    ApplicationUser query(RetrieveUserCommand command);

    @Value
    class RetrieveUserCommand {

        @NotEmpty(message = "User id is required")
        String userId;
    }
}
