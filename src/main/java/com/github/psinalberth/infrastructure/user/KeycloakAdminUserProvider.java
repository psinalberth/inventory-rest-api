package com.github.psinalberth.infrastructure.user;

import com.github.psinalberth.domain.shared.domain.exception.ElementNotFoundException;
import com.github.psinalberth.domain.user.application.domain.model.ApplicationUser;
import com.github.psinalberth.domain.user.application.port.incoming.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@Service
@RequiredArgsConstructor
public class KeycloakAdminUserProvider {

    private final UsersResource usersResource;
    private final UserRepresentationMapper userRepresentationMapper;

    public ApplicationUser update(ApplicationUser user) {
        UserResource us = usersResource.get(user.getUserId());
        us.update(new UserRepresentation());
        return userRepresentationMapper.convert(us.toRepresentation());
    }

    public ApplicationUser create(RegisterUserUseCase.RegisterUserCommand command) {
        UserRepresentation newUser = userRepresentationMapper.convert(command);
        Response response = usersResource.create(newUser);
        String userId = CreatedResponseUtil.getCreatedId(response);
        return retrieve(userId);
    }

    public ApplicationUser retrieve(String userId) {
        UserRepresentation userRepresentation;
        try {
            UserResource us = usersResource.get(userId);
            userRepresentation = us.toRepresentation();
        } catch (NotFoundException ex) {
            throw new ElementNotFoundException("User not found: " + userId);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }

        return userRepresentationMapper.convert(userRepresentation);
    }
}
