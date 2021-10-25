package com.github.psinalberth.domain.user.application.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationUser {

    private String userId;
    private String username;
    private String firstName;
    private String lastName;
}