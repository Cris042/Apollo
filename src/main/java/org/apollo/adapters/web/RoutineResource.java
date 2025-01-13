package org.apollo.adapters.web;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apollo.core.domain.models.Routine;
import org.apollo.core.port.in.ICompleteRoutineUseCase;
import org.apollo.core.port.in.ICreateRoutineUseCase;
import org.apollo.core.port.in.IDeleteRoutineUseCase;
import org.apollo.core.port.in.IUpdateRoutineUseCase;
import org.apollo.core.port.out.ILoadRoutinePort;

import java.time.LocalDateTime;
import java.util.List;


@Path("/api/routines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoutineResource {

    @Inject
    ICreateRoutineUseCase createRoutineUseCase;

    @Inject
    IUpdateRoutineUseCase updateRoutineUseCase;

    @Inject
    IDeleteRoutineUseCase deleteRoutineUseCase;

    @Inject
    ICompleteRoutineUseCase completeRoutineUseCase;

    @Inject
    ILoadRoutinePort loadRoutinePort;

    @GET
    @RolesAllowed("user")
    public List<Routine> getAllRoutines(@QueryParam("userId") String userId) {
        return loadRoutinePort.loadAllRoutines(userId);
    }

    @GET
    @Path("/{name}")
    @RolesAllowed("user")
    public Routine getRoutine(@PathParam("name") String name, @QueryParam("userId") String userId) {
        return loadRoutinePort.loadRoutineByName(name, userId);
    }

    @POST
    @RolesAllowed("user")
    public Response createRoutine(Routine routine) {
        Routine created = createRoutineUseCase.createRoutine(routine);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("user")
    public Routine updateRoutine(@PathParam("id") Long id, Routine routine) {
        return updateRoutineUseCase.updateRoutine(id, routine);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("user")
    public Response deleteRoutine(@PathParam("id") Long id, @QueryParam("userId") String userId) {
        deleteRoutineUseCase.deleteRoutine(id, userId);
        return Response.noContent().build();
    }


    @POST
    @Path("/{id}/complete")
    @RolesAllowed("user")
    public double completeRoutine(@PathParam("id") Long id, @QueryParam("startTime") String startTime, @QueryParam("endTime") String endTime) {
        LocalDateTime end = LocalDateTime.parse(endTime);
        LocalDateTime start = LocalDateTime.parse(startTime);
        return completeRoutineUseCase.completeRoutine(id, start, end);
    }
}
