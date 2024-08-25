package org.example.ecxeptions;

public class CaesarsCipherException extends RuntimeException{
    public CaesarsCipherException(String message) {
        super(message);
    }
}
