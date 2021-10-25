package com.github.psinalberth.domain.user.adapters.web;

import com.github.psinalberth.domain.user.application.domain.model.ApplicationUser;
import com.github.psinalberth.domain.user.application.port.incoming.RegisterUserUseCase;
import com.github.psinalberth.domain.user.application.port.incoming.QueryUserUseCase;
import com.github.psinalberth.domain.user.application.port.incoming.RegisterUserUseCase.RegisterUserCommand;
import com.github.psinalberth.domain.user.application.port.incoming.QueryUserUseCase.RetrieveUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final QueryUserUseCase queryUserUseCase;

    @PostMapping
    public ApplicationUser register(@Valid @RequestBody RegisterUserCommand command) {
        return registerUserUseCase.register(command);
    }

    @GetMapping(value = "/{userId}")
    public ApplicationUser retrieve(@PathVariable String userId) {
        RetrieveUserCommand command = new RetrieveUserCommand(userId);
        return queryUserUseCase.query(command);
    }
}