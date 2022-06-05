package dev.bstk.gatwayapi.domain.helper;

import javax.ws.rs.core.Response;

public class HttpStatusHelper {

    private HttpStatusHelper() { }

    public static boolean ok(final int statusCode) {
        return Response.Status.OK.getStatusCode() == statusCode;
    }

    public static boolean serverError(final int statusCode) {
        return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode() == statusCode;
    }

    public static boolean clientError(final int statusCode) {
        return Response.Status.BAD_REQUEST.getStatusCode() == statusCode;
    }
}
