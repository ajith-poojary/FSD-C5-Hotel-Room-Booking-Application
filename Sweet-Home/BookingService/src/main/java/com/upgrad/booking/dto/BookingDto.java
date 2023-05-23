package com.upgrad.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@JsonPropertyOrder({"id", "fromDate", "toDate", "aadharNumber", "numOfRooms", "roomNumbers", "roomPrice", "transactionId", "bookedOn"})
public class BookingDto {
    private int Id;


    private LocalDate fromDate;


    private LocalDate toDate;
    private String aadharNumber;

    private int numOfRooms;

    private String roomNumbers;

    private int roomPrice;

    private int transactionId=0;

    private LocalDateTime bookedOn;


}
