package org.example.ecxeptions;

public class CaesarsCipherException extends RuntimeException{
    String message;
    public CaesarsCipherException(String message) {
        this.message = message;
    }

    public CaesarsCipherException(String message, Throwable cause) {
        super(cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
