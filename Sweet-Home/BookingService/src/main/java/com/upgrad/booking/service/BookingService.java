package com.upgrad.booking.service;

import com.upgrad.booking.entities.Booking;

import java.util.List;

public interface BookingService {

    //create the booking

    Booking makeBooking(Booking booking);

    //get all the booking details

   List<Booking> getAll();


    // get the booking details , basedon booking Id

    Booking getBookingById(int bookingId);

    // create the transaction endpoint


}
