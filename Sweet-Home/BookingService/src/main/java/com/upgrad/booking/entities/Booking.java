package com.upgrad.booking.entities;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;

    @Column(name ="fromDate",nullable = true)
    private LocalDate fromDate;

    @Column(name ="toDate",nullable = true)
    private LocalDate toDate;

    @Column(name ="aadharNumber",nullable = true)
    private String aadharNumber;


    @Column(name ="numOfRoom")
    private int numOfRoom;

    @Column(name ="roomNumbers")
    private String roomNumbers;

    @Column(name ="roomPrice",nullable = false)
    private int roomPrice;

    @Column(name ="transacrionId")
    private int transacrionId=0;

    @Column(name ="bookedOn",nullable = true)
    @CreationTimestamp
    private LocalDateTime bookedOn;



}
