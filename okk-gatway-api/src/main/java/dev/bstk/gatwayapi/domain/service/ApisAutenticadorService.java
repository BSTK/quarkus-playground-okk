package dev.bstk.gatwayapi.domain.service;

import dev.bstk.gatwayapi.domain.helper.CollectionsHelper;
import dev.bstk.gatwayapi.domain.helper.HttpStatusHelper;
import dev.bstk.gatwayapi.domain.service.dto.ApisAutenticadorAcessTokenDto;
import dev.bstk.gatwayapi.resource.request.ConsultaApiTokenRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class ApisAutenticadorService {


    public ApisAutenticadorAcessTokenDto obterToken(@Valid final ConsultaApiTokenRequest apiRequest) {
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

        final Response response = requestBuilder.post(Entity.json(apiRequest.getPayload()));

        if (HttpStatusHelper.nok(response.getStatus())) {
            if (!response.hasEntity()) {
                throw new IllegalArgumentException("");
            }

            if (response.hasEntity()) {
                /// TODO: OBTER DADOS DA REQUISISÇÃO E INFORMAR NA EXCEPTION
                final Object dadosRequesicaoComErro = response.readEntity(Object.class);
                throw new IllegalArgumentException("Não foi possivél Acess Token.");
            }
        }

        return response.readEntity(ApisAutenticadorAcessTokenDto.class);
    }
}
