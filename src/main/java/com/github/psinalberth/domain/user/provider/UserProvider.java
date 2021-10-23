package com.github.psinalberth.domain.user.provider;

import com.github.psinalberth.domain.user.service.ApplicationUser;
import com.github.psinalberth.domain.user.service.RegisterUserRequest;
import com.github.psinalberth.domain.user.service.RegisterUserUseCase;

public interface UserProvider {

    ApplicationUser update(ApplicationUser user);

    ApplicationUser create(RegisterUserRequest request);

    ApplicationUser retrieve(String userId);

    ApplicationUser create(RegisterUserUseCase.RegisterUserCommand command);
}
