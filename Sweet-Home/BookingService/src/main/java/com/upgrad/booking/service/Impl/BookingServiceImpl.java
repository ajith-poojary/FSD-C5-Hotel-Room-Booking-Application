package com.upgrad.booking.service.Impl;

import com.upgrad.booking.dto.BookingDto;
import com.upgrad.booking.dto.TransactionDTO;
import com.upgrad.booking.entities.Booking;
import com.upgrad.booking.entities.Transaction;
import com.upgrad.booking.exceptions.InvalidPayMentOptionException;
import com.upgrad.booking.exceptions.ResourceNotFoundException;
import com.upgrad.booking.helper.HotelDesk;
import com.upgrad.booking.repositories.BookingServiceRepository;
import com.upgrad.booking.service.BookingService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ModelMapper modelMapper;

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

    @Override

    public Booking makePayment(Transaction transaction,int bookingId) {
//        //check is there a booking for the the booking Id
//        Booking booking = getBookingById(bookingId);
//
//
//
//
//        // pass the payLoad to payment service
//
//        String url= "http://localhost:8083/payment/transaction";
//        System.out.println("hitting  "+url);
//        ArrayList arrayList = restTemplate.postForObject(url, transaction, ArrayList.class);
//
//        System.out.println("Transcation 1 + "+arrayList);
//
//
//        return booking;

        int bookingId1 = transaction.getBookingId();

        if (bookingId1!=bookingId)
        {
           throw new ResourceNotFoundException("BookingId in the payload is not matching with what you providing it in your request "+bookingId);

        }
        Booking booking = getBookingById(bookingId);




            // Check if there is a booking for the given booking Id


        try {
            // Pass the payload to the payment service
            String url = "http://localhost:8083/payment/transaction";

            ResponseEntity<Integer> response = restTemplate.postForEntity(url, transaction, Integer.class);


                Integer resp = response.getBody();
                System.out.println("Transaction 1: " + resp);
                booking.setTransacrionId(resp);
            Booking updatedBooking = updateBooking(booking);
            return updatedBooking;

        }

            catch (RestClientException exception){

                throw new InvalidPayMentOptionException(exception.getMessage());

            }


        }

    @Override
    public Booking updateBooking(Booking booking) {
       return bookingServiceRepository.save(booking);
    }


}
