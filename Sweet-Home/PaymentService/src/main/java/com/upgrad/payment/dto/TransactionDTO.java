package com.upgrad.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private int transactionId;


    private String paymentMode;


    private int bookingId;


    private String upiId;


    private String cardNumber;

}
