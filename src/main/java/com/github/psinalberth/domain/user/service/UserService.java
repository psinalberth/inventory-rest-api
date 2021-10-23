package com.github.psinalberth.domain.user.service;

import com.github.psinalberth.domain.shared.annotation.UseCase;
import com.github.psinalberth.domain.user.provider.UserProvider;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UserService implements RegisterUserUseCase, RetrieveUserUseCase {

    private final UserProvider userProvider;

    @Override
    public ApplicationUser register(RegisterUserCommand command) {
        return userProvider.create(command);
    }

    @Override
    public ApplicationUser retrieve(RetrieveUserCommand command) {
        return userProvider.retrieve(command.getUserId());
    }
}
