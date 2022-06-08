package dev.bstk.gatwayapi.domain.exception;

public class ErrorAoObterUmAccessTokenException extends RuntimeException {

    private final Object response;
    public ErrorAoObterUmAccessTokenException(final String message,
                                              final Object response) {
        super(message);
        this.response = response;
    }

    public Object getResponse() {
        return response;
    }
}
