package dev.bstk.gatwayapi.domain.service;

import dev.bstk.gatwayapi.domain.helper.CollectionsHelper;
import dev.bstk.gatwayapi.domain.helper.StringHelper;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Map;

public final class ApisJaxRsHttpClient {

    private ApisJaxRsHttpClient() { }

    public static final class Builder {
        private String url;
        private Map<String, String> headers;
        private Map<String, String> queryParams;

        public static Builder builder() {
            return new Builder();
        }

        public Builder url(final String url) {
            validarUrl(url);
            this.url = url;
            return this;
        }

        public Builder headers(final Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder queryParams(final Map<String, String> queryParams) {
            this.queryParams = queryParams;
            return this;
        }

        public Invocation.Builder build() {
            validarUrl(url);

            final WebTarget webTarget = ClientBuilder
                .newClient()
                .target(url);

            if (CollectionsHelper.isNotEmpty(queryParams)) {
                queryParams.forEach(webTarget::queryParam);
            }

            final Invocation.Builder requestBuilder = webTarget.request(MediaType.APPLICATION_JSON);

            if (CollectionsHelper.isNotEmpty(headers)) {
                headers.forEach(requestBuilder::header);
            }

            return requestBuilder;
        }

        private void validarUrl(final String url) {
            if (StringHelper.isEmpty(url)) {
                throw new IllegalArgumentException("Url não pode ser nula ou vazia!");
            }
        }
    }
}

