package com.upgrad.booking.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int Id;

    @Column(name ="fromDate",nullable = true)

    private LocalDate fromDate;

    @Column(name ="toDate",nullable = true)

    private LocalDate toDate;

    @Column(name ="aadharNumber",nullable = true)
    private String aadharNumber;


    @Column(name ="numOfRoom")
    private int numOfRooms;

    @Column(name ="roomNumbers")
    private String roomNumbers;

    @Column(name ="roomPrice",nullable = false)
    private int roomPrice;

    @Column(name ="transactionId")
    private int transactionId=0;

    @Column(name ="bookedOn",nullable = true)
    @CreationTimestamp
    private LocalDateTime bookedOn;

    @Transient
    @JsonIgnore
    private Transaction transaction;

    @Override
    public String toString() {
        return "Booking{" +
                "Id=" + Id +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", aadharNumber='" + aadharNumber + '\'' +
                ", numOfRooms=" + numOfRooms +
                ", roomNumbers='" + roomNumbers + '\'' +
                ", roomPrice=" + roomPrice +
                ", transactionId=" + transactionId +
                ", bookedOn=" + bookedOn +
                '}';
    }
}
