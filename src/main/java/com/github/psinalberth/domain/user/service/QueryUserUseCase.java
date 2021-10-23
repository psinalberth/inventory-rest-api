package com.github.psinalberth.domain.user.service;

import com.github.psinalberth.domain.shared.annotation.UseCase;
import com.github.psinalberth.domain.user.provider.UserProvider;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@UseCase
@RequiredArgsConstructor
public class QueryUserUseCase {

    private final UserProvider userProvider;

    public ApplicationUser query(@NotEmpty String userId) {
        return userProvider.retrieve(userId);
    }
}
