package io.quarkus.workshop.superheroes.hero;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Path("/api/money_operations")
@Produces(APPLICATION_JSON)
public class OperationResource {
    private static final Logger LOGGER = Logger.getLogger(OperationResource.class.getName());

    @Inject
    OperationService service;

    @GET
    public Response getAllTransactions() {
        List<Operation> operations = service.findAllTransactions();
        LOGGER.debug("Total number of operations:\t" + operations.size());
        return Response.ok(operations).build();
    }

    @POST
    public Response createTransaction(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Operation.class))) 
        @Valid Operation operation, @Context UriInfo uriInfo) {
        operation = service.persistTransaction(operation);
        LOGGER.debug("New operation created with URI:\t" + operation.toString());
        return Response.ok(operation.id).build();
    }

    @PUT
    public Response updateTransaction(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Operation.class))) 
        @Valid Operation operation) {
        operation = service.updateTransaction(operation);
        LOGGER.debug("Transaction updated with new value:\t" + operation.toString());
        return Response.ok(operation).build();
    }
}
