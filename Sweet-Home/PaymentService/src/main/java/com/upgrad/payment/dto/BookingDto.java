package com.upgrad.payment.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class BookingDto {
    private int bookingId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String aadharNumber;

    private int numOfRooms;

    private String roomNumbers;

    private int roomPrice;

    private int transactionId=0;

    private LocalDateTime bookedOn;


}
