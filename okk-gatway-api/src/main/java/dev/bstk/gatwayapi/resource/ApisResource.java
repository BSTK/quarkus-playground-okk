package dev.bstk.gatwayapi.resource;

import dev.bstk.gatwayapi.domain.service.ApisService;
import dev.bstk.gatwayapi.resource.request.ConsultaApiRequest;
import dev.bstk.gatwayapi.resource.response.ConsultaApiResponse;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/apis")
@Tag(name = "Endpoint Apis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApisResource {

    @Inject
    protected ApisService apisService;

    @POST
    @Operation(summary = "Retorna dados das api's informadas na request")
    @Counted(name = "contadorPostApis", description = "Conta quantas vezes o método apis foi invocado")
    @Timed(
        name = "timerPostApis",
        description = "Quanto tempo leva para invocar o método apis",
        unit = MetricUnits.MILLISECONDS
    )
    @APIResponse(
        responseCode = "200",
        description = "Retorna os dados de response de cada api informada no payload de request",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON
        )
    )
    public Response apis(@Valid final ConsultaApiRequest request) {
        final ConsultaApiResponse consultaResponse = apisService.consultar(request);
        return Response
            .ok(consultaResponse)
            .build();
    }
}
