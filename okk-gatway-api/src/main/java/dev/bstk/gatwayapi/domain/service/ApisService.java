package dev.bstk.gatwayapi.domain.service;

import dev.bstk.gatwayapi.resource.request.ConsultaApiItemRequest;
import dev.bstk.gatwayapi.resource.request.ConsultaApiRequest;
import dev.bstk.gatwayapi.resource.response.ConsultaApiDadosItemResponse;
import dev.bstk.gatwayapi.resource.response.ConsultaApiResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ApisService {


    public ConsultaApiResponse consultar(@Valid final ConsultaApiRequest request) {
        List<ConsultaApiDadosItemResponse> consultaApiDados = new ArrayList<>();

        for (ConsultaApiItemRequest itemRequest : request.getApis()) {
            final Response response = ClientBuilder
                .newClient()
                .target(itemRequest.getUrl())
                .request(MediaType.APPLICATION_JSON)
                .get();

            final ConsultaApiDadosItemResponse itemResponse = new ConsultaApiDadosItemResponse(
                itemRequest.getUrl(),
                itemRequest.getNomeApiExterna());

            /// TODO: CASO DE SUCESSO
            if (Response.Status.OK.getStatusCode() == response.getStatus()) {
                itemResponse.setResponse(response.readEntity(Object.class));
            }

            /// TODO: CASO DE ERRO CLIENTE
            if (Response.Status.OK.getStatusCode() == response.getStatus()) {
                itemResponse.setResponse("OBJETO_CONTENDO_ERRO_CLIENTE");
            }

            /// TODO: CASO DE ERRO SERVIDOR
            if (Response.Status.OK.getStatusCode() == response.getStatus()) {
                itemResponse.setResponse("OBJETO_CONTENDO_ERRO_SERVIDOR");
            }

            consultaApiDados.add(itemResponse);
        }

        return new ConsultaApiResponse(consultaApiDados);
    }
}
