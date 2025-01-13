package org.apollo.adapters.in.rest;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apollo.domain.entities.Routine;
import org.apollo.application.port.in.service.ICompleteRoutineServicePort;
import org.apollo.application.port.in.service.ICreateRoutineServicePort;
import org.apollo.application.port.in.service.IDeleteRoutineServicePort;
import org.apollo.application.port.in.service.IUpdateRoutineServicePort;
import org.apollo.application.port.out.persistence.ILoadRoutinePersistencePort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Path("/api/routines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoutineRestController {

    @Inject
    ICreateRoutineServicePort createRoutineServicePort;

    @Inject
    IUpdateRoutineServicePort updateRoutineServicePort;

    @Inject
    IDeleteRoutineServicePort deleteRoutineServicePort;

    @Inject
    ICompleteRoutineServicePort completeRoutineServicePort;

    @Inject
    ILoadRoutinePersistencePort loadRoutinePort;

    @GET

    public List<Routine> getAllRoutines(@QueryParam("userId") UUID userId) {
        return loadRoutinePort.loadAllRoutines(userId);
    }

    @GET
    @Path("/{name}")
    @RolesAllowed("user")
    public Routine getRoutine(@PathParam("name") String name, @QueryParam("userId") String userId) {
        return loadRoutinePort.loadRoutineById(name, userId);
    }

    @POST
    @RolesAllowed("user")
    public Response createRoutine(Routine routine) {
        Routine created = createRoutineServicePort.createRoutine(routine);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("user")
    public Routine updateRoutine(@PathParam("id") Long id, @Valid  Routine routine) {
        return updateRoutineServicePort.updateRoutine(id, routine);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("user")
    public Response deleteRoutine(@PathParam("id") Long id, @QueryParam("userId") String userId) {
        deleteRoutineServicePort.deleteRoutine(id, userId);
        return Response.noContent().build();
    }


    @POST
    @Path("/{id}/complete")
    @RolesAllowed("user")
    public double completeRoutine(@PathParam("id") Long id, @QueryParam("startTime") String startTime, @QueryParam("endTime") String endTime) {
        LocalDateTime end = LocalDateTime.parse(endTime);
        LocalDateTime start = LocalDateTime.parse(startTime);
        return completeRoutineServicePort.completeRoutine(id, start, end);
    }
}
