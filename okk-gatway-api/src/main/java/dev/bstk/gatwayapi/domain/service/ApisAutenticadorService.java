package dev.bstk.gatwayapi.domain.service;

import dev.bstk.gatwayapi.domain.helper.HttpStatusHelper;
import dev.bstk.gatwayapi.domain.service.dto.ApisAutenticadorAcessTokenDto;
import dev.bstk.gatwayapi.resource.request.ConsultaApiTokenRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class ApisAutenticadorService {


    public ApisAutenticadorAcessTokenDto obterToken(@Valid final ConsultaApiTokenRequest apiRequest) {
        final Invocation.Builder request = ApisJaxRsHttpClient
            .Builder
            .builder()
            .url(apiRequest.getUrl())
            .headers(apiRequest.getHeaders())
            .queryParams(apiRequest.getParametros())
            .build();

        final Response response = request.post(Entity.json(apiRequest.getPayload()));

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
