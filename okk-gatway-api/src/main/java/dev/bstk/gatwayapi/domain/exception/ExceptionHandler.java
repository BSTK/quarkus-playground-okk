package dev.bstk.gatwayapi.domain.exception;

import io.quarkus.arc.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@ApplicationScoped
public final class ExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);

    private ExceptionHandler() { }

    @Provider
    @Priority(99999)
    public static class ErrorMapperErrorAoObterUmAccessToken implements ExceptionMapper<ErrorAoObterUmAccessTokenException> {
        @Override
        public Response toResponse(final ErrorAoObterUmAccessTokenException exception) {
            LOG.warn(exception.getMessage());

            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(exception.getResponse())
                .build();
        }
    }
}
