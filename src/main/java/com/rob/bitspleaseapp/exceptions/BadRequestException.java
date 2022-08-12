package com.rob.bitspleaseapp.exceptions;




public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(message);
    }
    public BadRequestException() {
        super("Bad request.");
    }
}