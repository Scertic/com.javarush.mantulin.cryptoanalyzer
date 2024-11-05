package com.javarush.mantulin.cryptoanalyzer.entity.ecxeptions;

public class CaesarsCipherException extends RuntimeException{
    String message;
    public CaesarsCipherException(String message) {
        this.message = message;
    }

    public CaesarsCipherException(String message, Throwable cause) {
        super(cause);
        this.message = message;
    }

    public CaesarsCipherException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
