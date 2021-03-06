package dev.bstk.exportadorapipdf.gateway;

import dev.bstk.exportadorapipdf.gateway.request.ConsultaApiRequest;
import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient
@Path("/okk-gateway/apis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ConsultaDadosGatewayApi {

    @POST
    ConsultaApiResponse apis(@Valid final ConsultaApiRequest request);
}
