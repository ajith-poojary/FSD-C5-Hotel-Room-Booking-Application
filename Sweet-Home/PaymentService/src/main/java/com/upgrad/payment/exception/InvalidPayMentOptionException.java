package com.upgrad.payment.exception;

public class InvalidPayMentOptionException extends RuntimeException {
    public InvalidPayMentOptionException(String message) {
        super(message);
    }
    public InvalidPayMentOptionException()
    {
        super("Invalid payment mode");
    }
}
