package com.github.psinalberth.domain.user.service;

import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class RegisterUserRequest {

    @NotEmpty
    String email;

    @NotEmpty
    String username;

    @NotEmpty
    String password;
}
