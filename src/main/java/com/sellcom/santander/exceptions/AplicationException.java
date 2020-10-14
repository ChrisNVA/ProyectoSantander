package com.sellcom.santander.exceptions;

public class AplicationException extends Exception {
    public AplicationException(String errorMessage) {
        super(errorMessage);
    }
    
    public AplicationException (Throwable cause) {
        super (cause);
    }

    public AplicationException (String message, Throwable cause) {
        super (message, cause);
    }
}
