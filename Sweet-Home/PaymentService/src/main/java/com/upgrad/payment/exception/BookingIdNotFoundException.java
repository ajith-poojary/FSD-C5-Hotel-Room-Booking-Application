package com.upgrad.payment.exception;

public class BookingIdNotFoundException extends RuntimeException {
    public BookingIdNotFoundException()
    {
        super("There is no booking for the mentionedId");
    }
    public BookingIdNotFoundException(String s) {
        super(s);
    }
}
