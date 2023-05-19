package com.upgrad.booking.controller;

import com.upgrad.booking.service.Impl.BookingServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel/booking")
public class BookingController {

    @Autowired
    BookingServiceImpl bookingService;

    @Autowired
    ModelMapper modelMapper;




}
