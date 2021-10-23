package com.github.psinalberth.infrastructure.user;

import com.github.psinalberth.domain.user.service.ApplicationUser;
import com.github.psinalberth.domain.user.service.RegisterUserRequest;
import com.github.psinalberth.domain.user.service.RegisterUserUseCase;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collections;

@Mapper
public interface UserRepresentationMapper {

    @Mapping(target = "enabled", constant = "true")
    UserRepresentation convert(RegisterUserRequest request);

    @Mapping(target = "enabled", constant = "true")
    UserRepresentation convert(RegisterUserUseCase.RegisterUserCommand command);

    @Mapping(source = "id", target = "userId")
    ApplicationUser convert(UserRepresentation user);

    @AfterMapping
    default void afterMapping(RegisterUserRequest request, @MappingTarget UserRepresentation userRepresentation) {
        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue(request.getPassword());
        credentials.setTemporary(false);
        userRepresentation.setCredentials(Collections.singletonList(credentials));
    }
}
