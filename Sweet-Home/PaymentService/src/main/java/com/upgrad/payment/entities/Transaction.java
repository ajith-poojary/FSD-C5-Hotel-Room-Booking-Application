package com.upgrad.payment.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "transcation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;

    @Column(name = "paymentMode")
    private String paymentMode;

    @Column(name = "bookingId",nullable = false)
    private int bookingId;

    @Column(name ="upiId",nullable = true )
    private String upiId;

    @Column(name = "cardNumber",nullable = true)
    private String cardNumber;


}
