package dev.bstk.gatwayapi.domain.service;

import dev.bstk.gatwayapi.domain.helper.HttpStatusHelper;
import dev.bstk.gatwayapi.domain.service.dto.ApisAutenticadorAcessTokenDto;
import dev.bstk.gatwayapi.domain.exception.ErrorAoObterUmAccessTokenException;
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
            .queryParams(apiRequest.getQueryParams())
            .build();

        final Response response = request.post(Entity.json(apiRequest.getPayload()));

        if (HttpStatusHelper.nok(response.getStatus())) {
            final Object dadosRequesicaoComErro = response.readEntity(Object.class);
            throw new ErrorAoObterUmAccessTokenException("Não foi possivél Acess Token.", dadosRequesicaoComErro);
        }

        return response.readEntity(ApisAutenticadorAcessTokenDto.class);
    }
}
