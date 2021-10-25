package com.github.psinalberth.infrastructure.user;

import com.github.psinalberth.domain.user.application.domain.model.ApplicationUser;
import com.github.psinalberth.domain.user.application.port.incoming.RegisterUserUseCase;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserRepresentationMapper {

    @Mapping(target = "enabled", constant = "true")
    UserRepresentation convert(RegisterUserUseCase.RegisterUserCommand command);

    @Mapping(source = "id", target = "userId")
    ApplicationUser convert(UserRepresentation user);

//    @AfterMapping
//    default void afterMapping(RegisterUserRequest request, @MappingTarget UserRepresentation userRepresentation) {
//        CredentialRepresentation credentials = new CredentialRepresentation();
//        credentials.setType(CredentialRepresentation.PASSWORD);
//        credentials.setValue(request.getPassword());
//        credentials.setTemporary(false);
//        userRepresentation.setCredentials(Collections.singletonList(credentials));
//    }
}
