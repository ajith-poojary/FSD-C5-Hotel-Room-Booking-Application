package com.upgrad.booking.controller;

import com.upgrad.booking.dto.BookingDto;
import com.upgrad.booking.entities.Booking;
import com.upgrad.booking.service.Impl.BookingServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hotel/booking")
public class BookingController {

    Logger logger= LoggerFactory.getLogger(BookingController.class);

    @Autowired
    BookingServiceImpl bookingService;

    @Autowired
    ModelMapper modelMapper;

    /**
     *
       POST :  localhost:8081/hotel/booking
     */
    @PostMapping
    public ResponseEntity<BookingDto> makeBooking(@RequestBody BookingDto bookingDto) {


//        LocalDateTime fromDate = LocalDateTime.parse(bookingDto.getFromDate() + "T00:00:00.000", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//        LocalDateTime toDate = LocalDateTime.parse(bookingDto.getToDate() + "T00:00:00.000", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//
//        System.out.println(fromDate +"{}"+toDate);
//
//        bookingDto.setFromDate(fromDate);
//        bookingDto.setToDate(toDate);


        Booking booking = modelMapper.map(bookingDto, Booking.class);
        Booking savedBooking = bookingService.makeBooking(booking);
        BookingDto savedBookingDto = modelMapper.map(savedBooking, BookingDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBookingDto);
    }

    /**
    GET :  localhost:8081/hotel/booking
     **/

    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBooking()
    {
        List<Booking> bookingList = bookingService.getAll();
        List<BookingDto> bookingDtos=new ArrayList<>();

        bookingList.forEach(booking -> {
            bookingDtos.add(modelMapper.map(booking, BookingDto.class));
        });

        return ResponseEntity.ok(bookingDtos);
    }

   // GET :  localhost:8081/hotel/booking/1

    @GetMapping(value = "/{bookingId}")
    public ResponseEntity<BookingDto> getBookingByBookingId(@PathVariable(name = "bookingId") int bookingId)
    {
        Booking booking = bookingService.getBookingById(bookingId);
        BookingDto bookingDto = modelMapper.map(booking, BookingDto.class);

        return ResponseEntity.ok(bookingDto);

    }


    
    






}
