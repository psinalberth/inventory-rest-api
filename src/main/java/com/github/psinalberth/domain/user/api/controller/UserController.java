package com.github.psinalberth.domain.user.api.controller;

import com.github.psinalberth.domain.user.service.*;
import com.github.psinalberth.domain.user.service.RegisterUserUseCase.RegisterUserCommand;
import com.github.psinalberth.domain.user.service.RetrieveUserUseCase.RetrieveUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final RetrieveUserUseCase retrieveUserUseCase;

    @PostMapping
    public ApplicationUser register(@Valid @RequestBody RegisterUserCommand command) {
        return registerUserUseCase.register(command);
    }

    @GetMapping(value = "/{userId}")
    public ApplicationUser retrieve(@PathVariable String userId) {
        RetrieveUserCommand command = new RetrieveUserCommand(userId);
        return retrieveUserUseCase.retrieve(command);
    }
}