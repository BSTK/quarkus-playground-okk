package dev.bstk.gatwayapi.domain.service;

import dev.bstk.gatwayapi.resource.request.ConsultaApiItemRequest;
import dev.bstk.gatwayapi.resource.request.ConsultaApiRequest;
import dev.bstk.gatwayapi.resource.response.ConsultaApiDadosItemResponse;
import dev.bstk.gatwayapi.resource.response.ConsultaApiResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static dev.bstk.gatwayapi.domain.helper.HttpStatusHelper.*;

@ApplicationScoped
public class ApisService {


    public ConsultaApiResponse consultar(@Valid final ConsultaApiRequest apiRequest) {
        final List<ConsultaApiDadosItemResponse> consultaApiDados = new ArrayList<>();

        for (ConsultaApiItemRequest itemRequest : apiRequest.getApis()) {
            final Invocation.Builder request = ApisJaxRsHttpClient
                .Builder
                .builder()
                .url(itemRequest.getUrl())
                .headers(itemRequest.getHeaders())
                .queryParams(itemRequest.getParametros())
                .build();

            final Response response = request.get();

            final ConsultaApiDadosItemResponse itemResponse = new ConsultaApiDadosItemResponse(
                itemRequest.getUrl(),
                itemRequest.getNomeApiExterna());

            if (ok(response.getStatus())) {
                itemResponse.setResponse(response.readEntity(Object.class));
            }

            if (clientError(response.getStatus())) {
                itemResponse.setResponse("OBJETO_CONTENDO_ERRO_CLIENTE");
            }

            if (serverError(response.getStatus())) {
                itemResponse.setResponse("OBJETO_CONTENDO_ERRO_SERVIDOR");
            }

            consultaApiDados.add(itemResponse);
        }

        return new ConsultaApiResponse(consultaApiDados);
    }
}
