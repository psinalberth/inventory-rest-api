package com.github.psinalberth.domain.user.application.port.incoming;

import com.github.psinalberth.domain.user.application.domain.model.ApplicationUser;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public interface RegisterUserUseCase {

    ApplicationUser register(@Valid RegisterUserCommand command);

    @Value
    class RegisterUserCommand {

        @NotEmpty(message = "First name is required")
        String firstName;

        @NotEmpty(message = "Last name is required")
        String lastName;

        @NotEmpty(message = "Email is required")
        String email;

        @NotEmpty(message = "Username is required")
        String username;

        @NotEmpty(message = "Password is required")
        String password;
    }
}
