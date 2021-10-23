package com.github.psinalberth.domain.user.service;


import com.github.psinalberth.domain.shared.annotation.UseCase;
import com.github.psinalberth.domain.user.provider.UserProvider;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserProvider userProvider;

    public ApplicationUser register(RegisterUserRequest request) {
        return userProvider.create(request);
    }
}