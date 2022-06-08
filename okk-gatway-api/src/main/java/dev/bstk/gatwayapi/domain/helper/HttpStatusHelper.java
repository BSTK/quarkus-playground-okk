package dev.bstk.gatwayapi.domain.helper;

import javax.ws.rs.core.Response.Status.Family;

public final class HttpStatusHelper {

    private HttpStatusHelper() { }

    public static boolean ok(final int statusCode) {
        return Family.SUCCESSFUL.equals(Family.familyOf(statusCode));
    }

    public static boolean nok(final int statusCode) {
        return clientError(statusCode) || serverError(statusCode);
    }

    public static boolean clientError(final int statusCode) {
        return Family.CLIENT_ERROR.equals(Family.familyOf(statusCode));
    }

    public static boolean serverError(final int statusCode) {
        return Family.SERVER_ERROR.equals(Family.familyOf(statusCode));
    }
}
