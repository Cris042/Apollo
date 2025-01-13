package org.apollo.infrastructure.config.auth;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Map;
import java.util.logging.Logger;

@ApplicationScoped
public class KeycloakUserService {

    private static final Logger LOGGER = Logger.getLogger(KeycloakUserService.class.getName());

    @Inject
    @ConfigProperty(name = "keycloak.base-url")
    private String keycloakBaseUrl;

    @Inject
    @ConfigProperty(name = "keycloak.client-id")
    private String adminClientId;

    @Inject
    @ConfigProperty(name = "keycloak.client-secret")
    private String adminClientSecret;

    @Inject
    @ConfigProperty(name = "keycloak.realm")
    private String realm;

    private Client client;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
    }

    public boolean createUser(String username, String password, String email) {
        String token = getAdminToken();
        if (token == null) {
            LOGGER.severe("Falha ao obter o token do administrador.");
            return false;
        }

        String userJson = createUserJson(username, password, email);

        try (Response response = client.target(keycloakBaseUrl + "/admin/realms/" + realm + "/users")
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .post(Entity.json(userJson))) {

            if (response.getStatus() == 201) {
                LOGGER.info("Usuário criado com sucesso.");

                String location = response.getHeaderString("Location");
                String userId = location.substring(location.lastIndexOf("/") + 1);

                if (assignRoleToUser(userId, token, "user")) {
                    LOGGER.info("Papel 'user' atribuído com sucesso ao usuário.");
                    return true;
                } else {
                    LOGGER.warning("Falha ao atribuir o papel 'user' ao usuário.");
                }
            } else {
                LOGGER.warning("Falha ao criar usuário. Status: " + response.getStatus());
                LOGGER.warning("Resposta: " + response.readEntity(String.class));
            }
        }
        return false;
    }

    private boolean assignRoleToUser(String userId, String token, String roleName) {

        try (Response response = client.target(keycloakBaseUrl + "/admin/realms/" + realm + "/roles/" + roleName)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .get()) {

            if (response.getStatus() == 200) {
                Map<String, Object> role = response.readEntity(Map.class);


                try (Response assignResponse = client.target(keycloakBaseUrl + "/admin/realms/" + realm + "/users/" + userId + "/role-mappings/realm")
                        .request(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .post(Entity.json(new Map[]{role}))) {

                    return assignResponse.getStatus() == 204;
                }
            } else {
                LOGGER.warning("Falha ao obter detalhes do papel '" + roleName + "'. Status: " + response.getStatus());
                LOGGER.warning("Resposta: " + response.readEntity(String.class));
            }
        }
        return false;
    }

    public String authenticateUser(String username, String password) {
        try (Response response = client.target(keycloakBaseUrl + "/realms/" + realm + "/protocol/openid-connect/token")
                .request(MediaType.APPLICATION_FORM_URLENCODED)
                .post(Entity.form(new jakarta.ws.rs.core.Form()
                        .param("client_id", adminClientId)
                        .param("client_secret", adminClientSecret)
                        .param("grant_type", "password")
                        .param("username", username)
                        .param("password", password)))) {

            if (response.getStatus() == 200) {
                Map<String, Object> tokenResponse = response.readEntity(Map.class);
                return tokenResponse.get("access_token").toString();
            } else {
                LOGGER.warning("Falha ao autenticar usuário. Status: " + response.getStatus());
                LOGGER.warning("Resposta: " + response.readEntity(String.class));
                return null;
            }
        }
    }


    private String getAdminToken() {
        try (Response response = client.target(keycloakBaseUrl + "/realms/master/protocol/openid-connect/token")
                .request(MediaType.APPLICATION_FORM_URLENCODED)
                .post(Entity.form(new jakarta.ws.rs.core.Form()
                        .param("client_id", adminClientId)
                        .param("client_secret", adminClientSecret)
                        .param("grant_type", "client_credentials")))) {

            if (response.getStatus() == 200) {
                Map<String, Object> tokenResponse = response.readEntity(Map.class);
                return tokenResponse.get("access_token").toString();
            } else {
                LOGGER.severe("Falha ao obter token de administrador. Status: " + response.getStatus());
                LOGGER.severe("Resposta: " + response.readEntity(String.class));
                return null;
            }
        }
    }

    private String createUserJson(String username, String password, String email) {
        return String.format("""
                {
                    "username": "%s",
                    "email": "%s",
                    "enabled": true,
                    "credentials": [
                        {
                            "type": "password",
                            "value": "%s",
                            "temporary": false
                        }
                    ]
                }
                """, username, email, password);
    }
}
