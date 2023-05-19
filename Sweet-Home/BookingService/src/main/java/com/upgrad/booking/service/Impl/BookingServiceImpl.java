package com.upgrad.booking.service.Impl;

import com.upgrad.booking.entities.Booking;
import com.upgrad.booking.exceptions.ResourceNotFoundException;
import com.upgrad.booking.repositories.BookingServiceRepository;
import com.upgrad.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {


    @Autowired
    BookingServiceRepository bookingServiceRepository;

    @Override
    public Booking makeBooking(Booking booking) {
        return bookingServiceRepository.save(booking);
    }

    @Override
    public List<Booking> getAll() {
        return bookingServiceRepository.findAll();
    }

    @Override
    public Booking getBookingById(int bookingId) {
        return bookingServiceRepository.findById(bookingId).orElseThrow(()->new ResourceNotFoundException("There is no booking for the provided Id "+bookingId));
    }
}
