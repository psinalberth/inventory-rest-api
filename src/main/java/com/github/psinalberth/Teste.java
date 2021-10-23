package com.github.psinalberth;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.util.Collections;

public class Teste {

    public static void main(String[] args) {

        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8081/auth")
                .realm("inventario")
                .clientId("app-client")
                .clientSecret("6c62de91-30a7-4b5f-8915-5509d788e683")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();

        RealmResource realmResource = keycloak.realm("inventario");
        UsersResource usersResource = realmResource.users();

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue("123");

        UserRepresentation user = new UserRepresentation();
        user.setUsername("jdoe");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setEnabled(true);
        user.setEmailVerified(false);
        user.setCredentials(Collections.singletonList(credentialRepresentation));

        Response response = usersResource.create(user);
        System.out.printf("Repsonse: %s %s%n", response.getStatus(), response.getStatusInfo());
        System.out.println(response.getLocation());
        String userId = CreatedResponseUtil.getCreatedId(response);

        System.out.printf("User created with userId: %s%n", userId);
        System.out.println(keycloak.tokenManager().getAccessToken().getToken());

        usersResource.search("jdoe")
            .forEach(userRepresentation -> System.out.println(userRepresentation.getFirstName()));
    }
}
