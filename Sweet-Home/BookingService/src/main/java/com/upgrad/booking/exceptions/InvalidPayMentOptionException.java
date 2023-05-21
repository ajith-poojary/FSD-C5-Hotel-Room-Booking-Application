package com.upgrad.booking.exceptions;

public class InvalidPayMentOptionException extends RuntimeException {
    public InvalidPayMentOptionException()
    {
        super("Invalid Payment");

    }
    public InvalidPayMentOptionException(String message)
    {
        super(message);
    }
}
