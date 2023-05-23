package com.upgrad.booking.exceptions;

import com.upgrad.booking.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception)
    {
        String message= exception.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).statusCode(400).build();

        return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPayMentOptionException.class)
    public  ResponseEntity<ApiResponse> handlePaymentException(InvalidPayMentOptionException exception)
    {
        String message= exception.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).statusCode(400).build();

        return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);

    }
}
