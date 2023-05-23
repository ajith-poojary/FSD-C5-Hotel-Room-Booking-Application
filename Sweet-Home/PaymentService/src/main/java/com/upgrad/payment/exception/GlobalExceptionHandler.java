package com.upgrad.payment.exception;

import com.upgrad.payment.payload.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPayMentOptionException.class)
    public ResponseEntity<ApiResponse> handleInvalidPayment(InvalidPayMentOptionException exception)
    {
        String message= exception.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).statusCode(400).build();

        return ResponseEntity.badRequest().body(response);

    }
    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ApiResponse> handleInvalidTransaction(TransactionNotFoundException exception)
    {
        String message= exception.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).statusCode(400).build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BookingIdNotFoundException.class)
    public ResponseEntity<ApiResponse> handleInvalidTransaction(BookingIdNotFoundException exception)
    {
        String message= exception.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).statusCode(400).build();
        return ResponseEntity.badRequest().body(response);
    }

}
