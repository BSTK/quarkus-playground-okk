package dev.bstk.gatwayapi.domain.service;

import dev.bstk.gatwayapi.domain.helper.CollectionsHelper;
import dev.bstk.gatwayapi.resource.request.ConsultaApiItemRequest;
import dev.bstk.gatwayapi.resource.request.ConsultaApiRequest;
import dev.bstk.gatwayapi.resource.response.ConsultaApiDadosItemResponse;
import dev.bstk.gatwayapi.resource.response.ConsultaApiResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static dev.bstk.gatwayapi.domain.helper.HttpStatusHelper.*;

@ApplicationScoped
public class ApisService {


    public ConsultaApiResponse consultar(@Valid final ConsultaApiRequest request) {
        final List<ConsultaApiDadosItemResponse> consultaApiDados = new ArrayList<>();

        for (ConsultaApiItemRequest apiRequest : request.getApis()) {
            final WebTarget webTarget = ClientBuilder
                .newClient()
                .target(apiRequest.getUrl());

            if (CollectionsHelper.isNotEmpty(apiRequest.getParametros())) {
                apiRequest
                    .getParametros()
                    .forEach(webTarget::queryParam);
            }

            final Invocation.Builder requestBuilder = webTarget.request(MediaType.APPLICATION_JSON);

            if (CollectionsHelper.isNotEmpty(apiRequest.getHeaders())) {
                apiRequest
                    .getHeaders()
                    .forEach(requestBuilder::header);
            }

            final Response response = requestBuilder.get();

            final ConsultaApiDadosItemResponse itemResponse = new ConsultaApiDadosItemResponse(
                apiRequest.getUrl(),
                apiRequest.getNomeApiExterna());

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
