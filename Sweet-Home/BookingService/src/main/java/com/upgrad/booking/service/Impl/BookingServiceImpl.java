package com.upgrad.booking.service.Impl;


import com.upgrad.booking.entities.Booking;
import com.upgrad.booking.entities.Transaction;
import com.upgrad.booking.exceptions.InvalidPayMentOptionException;
import com.upgrad.booking.exceptions.ResourceNotFoundException;
import com.upgrad.booking.helper.HotelDesk;
import com.upgrad.booking.repositories.BookingServiceRepository;
import com.upgrad.booking.service.BookingService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    Logger logger= LoggerFactory.getLogger(BookingServiceImpl.class);


    @Autowired
    BookingServiceRepository bookingServiceRepository;

    @Autowired
    HotelDesk helper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ModelMapper modelMapper;

    @Autowired

    private LoadBalancerClient loadBalancerClient;


    @Override
    public Booking makeBooking(Booking booking) {
        ArrayList<String> availableRooms = helper.getRandomNumbers(booking.getNumOfRooms());
        System.out.println("Available Rooms " + availableRooms);
        String availableRoom = String.join(",", availableRooms);
        booking.setRoomNumbers(availableRoom);
        LocalDate fromDate = booking.getFromDate();
        LocalDate toDate = booking.getToDate();


        int NumberOfdays = Period.between(fromDate, toDate).getDays();
        int price = helper.calculatePrice(NumberOfdays, booking.getNumOfRooms());


        booking.setRoomPrice(price);


        return bookingServiceRepository.save(booking);
    }

    @Override
    public List<Booking> getAll() {
        return bookingServiceRepository.findAll();
    }

    @Override
    public Booking getBookingById(int bookingId) {
        return bookingServiceRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Invalid Booking Id"));
    }

    @Override

    public Booking makePayment(Transaction transaction, int bookingId) {

        int bookingId1 = transaction.getBookingId();

        if (bookingId1 != bookingId) {
            throw new ResourceNotFoundException("BookingId in the payload is not matching with what you providing it in your request " + bookingId);

        }
        Booking booking = getBookingById(bookingId);


        // Check if there is a booking for the given booking Id


        try {
            // Pass the payload to the payment service
//            String url = "http://localhost:8083/payment/transaction";


            ServiceInstance paymentServiceInstance = loadBalancerClient.choose("PAYMENT-SERVICE");

            if (paymentServiceInstance == null) {
                throw new IllegalStateException("Payment service not found in load balancer.");
            }
            String paymentEndUrl = paymentServiceInstance.getUri().toString() + "/payment/transaction";
            System.out.println(paymentEndUrl);


            ResponseEntity<Integer> response = restTemplate.postForEntity(paymentEndUrl, transaction, Integer.class);




            Integer resp = response.getBody();
            System.out.println("Transaction 1: " + resp);
            booking.setTransactionId(resp);


            updateBooking(booking);
            String message = "Booking confirmed for user with aadhaar number: "
                    + booking.getAadharNumber()
                    +    "    |    "
                    + "Here are the booking details:    " + booking.toString();

            logger.info("After transaction : ");
            logger.info(message);
//
//            Booking updatedBooking = updateBooking(booking);
//            return updatedBooking;
            return booking;

        }
        catch (RestClientException exception) {

            throw new InvalidPayMentOptionException("Invalid mode of payment");

        }


    }

    @Override
    public void updateBooking(Booking booking) {
        int bookingId = booking.getId();
        Booking retrievedBooking = getBookingById(bookingId);
        retrievedBooking.setId(booking.getId());
        retrievedBooking.setFromDate(booking.getFromDate());
        retrievedBooking.setToDate(booking.getToDate());
        retrievedBooking.setAadharNumber(booking.getAadharNumber());
        retrievedBooking.setNumOfRooms(booking.getNumOfRooms());
        retrievedBooking.setRoomNumbers(booking.getRoomNumbers());
        retrievedBooking.setRoomPrice(booking.getRoomPrice());
        retrievedBooking.setTransactionId(booking.getTransactionId());

        bookingServiceRepository.save(retrievedBooking);
    }

//    @Override
//    public Booking updateBooking(Booking booking) {
//        return bookingServiceRepository.save(booking);
//    }


}
