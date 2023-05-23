package com.upgrad.booking.service;

import com.upgrad.booking.entities.Booking;
import com.upgrad.booking.entities.Transaction;

import java.util.List;

public interface BookingService {

    //create the booking

    Booking makeBooking(Booking booking);

    //get all the booking details

   List<Booking> getAll();


    // get the booking details , basedon booking Id

    Booking getBookingById(int bookingId);

    // make transaction
    Booking makePayment(Transaction transaction,int bookingId);

   void updateBooking(Booking booking);


}
