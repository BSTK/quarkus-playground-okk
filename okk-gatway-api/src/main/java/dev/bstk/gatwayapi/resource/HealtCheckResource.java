package dev.bstk.gatwayapi.resource;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Tag(name = "Endpoint HealthChecke")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HealtCheckResource {


    @GET
    @Path("/up")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Retorna o estado do endpoint Api's")
    @APIResponse(
        responseCode = "200",
        description = "Retorna 'UP' para quando o enpoint estiver respondendo Ok",
        content = @Content(
            mediaType = MediaType.TEXT_PLAIN
        )
    )
    public Response ping() {
        return Response.ok("up").build();
    }
}
