package dev.bstk.gatwayapi.apis;

import dev.bstk.gatwayapi.apis.request.ConsultaApiRequest;
import dev.bstk.gatwayapi.apis.response.ConsultaApiDadosItemResponse;
import dev.bstk.gatwayapi.apis.response.ConsultaApiResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;

@ApplicationScoped
public class ApisService {


    public ConsultaApiResponse consultar(@Valid final ConsultaApiRequest request) {
        final var consultaApiDados = new ArrayList<ConsultaApiDadosItemResponse>();

        for (final var itemRequest : request.getApis()) {
            final var response = ClientBuilder
                .newClient()
                .target(itemRequest.getUrl())
                .request(MediaType.APPLICATION_JSON)
                .get();

            final var itemResponse = new ConsultaApiDadosItemResponse();
            itemResponse.setUrl(itemRequest.getUrl());
            itemResponse.setNomeApiExterna(itemRequest.getNomeApiExterna());

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

        final var consultaApiResponse = new ConsultaApiResponse();
        consultaApiResponse.setDados(consultaApiDados);
        consultaApiResponse.setDataHoraRequest(LocalDateTime.now());

        return consultaApiResponse;
    }
}
