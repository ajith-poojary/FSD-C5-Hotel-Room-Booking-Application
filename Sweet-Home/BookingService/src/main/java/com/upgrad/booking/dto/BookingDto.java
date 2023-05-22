package com.upgrad.booking.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BookingDto {
    private int bookingId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String aadharNumber;

    private int numOfRoom;

    private String roomNumbers;

    private int roomPrice;

    private int transactionId=0;

    private LocalDateTime bookedOn;


}
