package com.upgrad.payment.service.Impl;

import com.upgrad.payment.dto.BookingDto;
import com.upgrad.payment.entities.Transaction;

import com.upgrad.payment.exception.BookingIdNotFoundException;
import com.upgrad.payment.exception.InvalidPayMentOptionException;
import com.upgrad.payment.exception.TransactionNotFoundException;

import com.upgrad.payment.repository.TransactionRepository;
import com.upgrad.payment.service.TransactionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    Logger logger= LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    TransactionRepository repository;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public int makeTransaction(Transaction transaction) {

        //get the bookingId
        int bookingId = transaction.getBookingId();


        //get the payment mode
        String paymentMode = transaction.getPaymentMode().toUpperCase();

        Transaction savedTransaction;

        if (!(paymentMode.equals("UPI") || paymentMode.equals("CARD")))
        {
            throw new InvalidPayMentOptionException("Invalid payment");

        }

        ServiceInstance bookingServiceInstance = loadBalancerClient.choose("BOOKING-SERVICE");

        if (bookingServiceInstance==null)
        {
            throw new IllegalStateException("Booking service not found in load balancer");
        }

        String bookingEndUrl = bookingServiceInstance.getUri().toString() + "/hotel/booking/" + bookingId;

        String bookingEndUpdteUrl=bookingServiceInstance.getUri().toString() +"/hotel/booking";

        System.out.println("bookingEnd Url {}"+bookingEndUrl);
        System.out.println("put method url{}{}"+bookingEndUpdteUrl);


        try
        {
            BookingDto booingDtoObject = restTemplate.getForObject(bookingEndUrl, BookingDto.class);

            System.out.println("Response entity :{}"+booingDtoObject);

             savedTransaction= repository.save(transaction);

            String message = "Booking confirmed for user with aadhaar number: "
                    + booingDtoObject.getAadharNumber()
                    +    "    |    "
                    + "Here are the booking details:    " + booingDtoObject.toString();

             logger.info(message);
//            int transactionId = savedTransaction.getTransactionId();
////
//            booingDtoObject.setTransactionId(transactionId);
//
//            restTemplate.put(bookingEndUpdteUrl,booingDtoObject);
//
//            ResponseEntity<Void> response = restTemplate.exchange(bookingEndUpdteUrl, HttpMethod.PUT, new HttpEntity<>(BookingDto.class), Void.class);

//            restTemplate.exchange(bookingEndUpdteUrl,booingDtoObject,BookingDto.class);

        }
        catch (RestClientException exception)
        {
            throw new BookingIdNotFoundException("Invalid Booking Id");
        }

        return savedTransaction.getTransactionId();

    }

    @Override
    public List<Transaction> getAll() {
        return repository.findAll();
    }


    @Override
    public Transaction getTransactionById(int transactionId) {
        return repository.findTranscationBytransactionId(transactionId).orElseThrow(() -> new TransactionNotFoundException("There is no transcation for this transaction id " + transactionId));

    }
}
