package com.upgrad.booking.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException()
    {
        super("Resource not found om Server!!");
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
