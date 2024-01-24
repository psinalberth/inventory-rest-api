package com.github.psinalberth.domain.user.adapters.web;

import com.github.psinalberth.domain.user.application.domain.model.ApplicationUser;
import com.github.psinalberth.domain.user.application.port.incoming.RegisterUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users", description = "Manage application users")
public interface UserControllerOpenApi {

    @Operation(summary = "Register new user")
    ApplicationUser register(RegisterUserUseCase.RegisterUserCommand command);

    @Operation(summary = "Retrieve user by id")
    ApplicationUser retrieve(String userId);
}
