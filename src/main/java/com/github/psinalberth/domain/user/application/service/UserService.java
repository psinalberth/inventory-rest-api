package com.github.psinalberth.domain.user.application.service;

import com.github.psinalberth.domain.shared.domain.annotation.UseCase;
import com.github.psinalberth.domain.user.application.domain.model.ApplicationUser;
import com.github.psinalberth.domain.user.application.port.incoming.QueryUserUseCase;
import com.github.psinalberth.domain.user.application.port.incoming.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@UseCase
@RequiredArgsConstructor
public class UserService implements RegisterUserUseCase, QueryUserUseCase {

    @Override
    public ApplicationUser query(RetrieveUserCommand command) {
        return null;
    }

    @Override
    public ApplicationUser register(@Valid RegisterUserCommand command) {
        return null;
    }
}
