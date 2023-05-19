package com.upgrad.booking.service.Impl;

import com.upgrad.booking.entities.Booking;
import com.upgrad.booking.exceptions.ResourceNotFoundException;
import com.upgrad.booking.helper.HotelDesk;
import com.upgrad.booking.repositories.BookingServiceRepository;
import com.upgrad.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {


    @Autowired
    BookingServiceRepository bookingServiceRepository;

    @Autowired
    HotelDesk helper;

    @Override
    public Booking makeBooking(Booking booking) {
        ArrayList<String> availableRooms = helper.getRandomNumbers(booking.getNumOfRoom());
        System.out.println("Available Rooms "+availableRooms);
        String availableRoom=String.join(",",availableRooms);
        booking.setRoomNumbers(availableRoom);
        LocalDate fromDate = booking.getFromDate();
        LocalDate toDate = booking.getToDate();
        int  NumberOfdays = Period.between(fromDate, toDate).getDays();
        int price = helper.calculatePrice(NumberOfdays, booking.getNumOfRoom());
        booking.setRoomPrice(price);


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
