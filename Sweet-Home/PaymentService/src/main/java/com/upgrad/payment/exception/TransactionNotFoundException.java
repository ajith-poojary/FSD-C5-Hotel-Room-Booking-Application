package com.upgrad.payment.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException()
    {
        super("This transactionId is not available in Server !!!");
    }
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
