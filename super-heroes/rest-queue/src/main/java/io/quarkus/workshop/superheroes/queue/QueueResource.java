package io.quarkus.workshop.superheroes.queue;

import javax.validation.Valid;
import javax.ws.rs.core.*;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import io.quarkus.workshop.superheroes.queue.Operation;
import io.quarkus.workshop.superheroes.queue.QueueService;

import javax.inject.Inject;
import javax.ws.rs.DELETE;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/queue")
@Produces(APPLICATION_JSON)
public class QueueResource {
    private static final Logger LOGGER = Logger.getLogger(QueueResource.class.getName());

    @Inject
    QueueService service;

    @POST
    public Response createOperation(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Operation.class))) 
        @Valid Operation operation, @Context UriInfo uriInfo) {
        operation = service.persistOperation(operation);
        LOGGER.debug("New operation created with URI:\t" + operation.toString());
        return Response.ok(operation.id).build();
    }

    @DELETE
    public Response popOperation(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Operation.class))) 
        @Valid Operation operation, @Context UriInfo uriInfo) {
        return Response.ok(service.deleteOperation(operation.id)).build();
    }
}