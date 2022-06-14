package dev.bstk.exportadorapipdf.domain;

import dev.bstk.exportadorapipdf.domain.gateway.ConsultaDadosGatwayApi;
import dev.bstk.exportadorapipdf.domain.gateway.request.ConsultaApiItemRequest;
import dev.bstk.exportadorapipdf.domain.gateway.request.ConsultaApiRequest;
import dev.bstk.exportadorapipdf.domain.gateway.response.ConsultaApiResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ApplicationScoped
public class ExportarPdfService {

    private static final Logger LOG = LoggerFactory.getLogger(ExportarPdfService.class);

    @Inject
    @RestClient
    protected ConsultaDadosGatwayApi consultaDadosGatwayApi;


    public void executar() {
        LOG.info("Exportando dados PDF");

        final ConsultaApiItemRequest apiAAARequest = new ConsultaApiItemRequest();
        apiAAARequest.setNomeApiExterna("Genius Api");
        apiAAARequest.setUrl("https://genius.p.rapidapi.com/artists/16775/songs");
        apiAAARequest.setHeaders(Map.of(
            "X-RapidAPI-Key", "386fb7f127msh1a05d6cfe648366p1662d5jsn91546411e79e",
            "X-RapidAPI-Host", "genius.p.rapidapi.com"
        ));

        final ConsultaApiRequest request = new ConsultaApiRequest();
        request.setAppCliente("EXPORTADOR PDF");
        request.setApis(List.of(apiAAARequest));

        final ConsultaApiResponse response = consultaDadosGatwayApi.apis(request);
        if (Objects.isNull(response)) {
            throw new IllegalStateException("Response inválida! Reprocessar!");
        }
    }
}