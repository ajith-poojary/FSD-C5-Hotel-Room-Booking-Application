package com.upgrad.booking.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {


    private int transactionId;


    private String paymentMode;


    private int bookingId;


    private String upiId;


    private String cardNumber;


}
