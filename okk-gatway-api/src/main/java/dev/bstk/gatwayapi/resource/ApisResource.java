package dev.bstk.gatwayapi.resource;

import dev.bstk.gatwayapi.domain.service.ApisService;
import dev.bstk.gatwayapi.resource.request.ConsultaApiRequest;
import dev.bstk.gatwayapi.resource.response.ConsultaApiResponse;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/apis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApisResource {

    @Inject
    protected ApisService apisService;

    @POST
    public Response apis(@Valid final ConsultaApiRequest request) {
        final ConsultaApiResponse consultaResponse = apisService.consultar(request);
        return Response
            .ok(consultaResponse)
            .build();
    }
}
