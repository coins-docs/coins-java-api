package com.coins.api.exception;

/**
 * Base exception for Coins API operations
 */
public class CoinsApiException extends Exception {
    private final int code;

    public CoinsApiException(String message) {
        super(message);
        this.code = -1;
    }

    public CoinsApiException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CoinsApiException(String message, Throwable cause) {
        super(message, cause);
        this.code = -1;
    }

    public int getCode() {
        return code;
    }

    // No need to override getMessage() as we use the parent class's implementation
}
