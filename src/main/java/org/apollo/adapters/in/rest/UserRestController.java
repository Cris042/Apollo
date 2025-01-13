package org.apollo.adapters.in.rest;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apollo.adapters.dto.UserDTO;
import org.apollo.infrastructure.config.auth.KeycloakUserService;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRestController {

    @Inject
    JsonWebToken jwt;

    @Inject
    KeycloakUserService keycloakUserService;


    @POST
    @Path("/register")
    public Response registerUser(UserDTO user) {
        boolean success = keycloakUserService.createUser(user.getUsername(), user.getPassword(), user.getEmail());
        if (success) {
            return Response.status(Response.Status.CREATED).entity("Usuário registrado com sucesso!").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao registrar usuário.").build();
        }
    }

    @POST
    @Path("/login")
    public Response loginUser(UserDTO user) {
        String token = keycloakUserService.authenticateUser(user.getUsername(), user.getPassword());
        if (token != null) {
            return Response.ok().entity("{\"token\":\"" + token + "\"}").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciais inválidas.").build();
        }
    }


    @GET
    @Path("/profile")
    @RolesAllowed("user")
    public Response getProfile() {
        return Response.ok()
                .entity("Bem-vindo, " + jwt.getName() + "! Seu token está ativo.")
                .build();
    }
}