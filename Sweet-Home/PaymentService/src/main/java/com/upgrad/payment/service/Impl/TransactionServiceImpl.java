package com.upgrad.payment.service.Impl;

import com.upgrad.payment.entities.Transaction;

import com.upgrad.payment.exception.InvalidPayMentOptionException;
import com.upgrad.payment.exception.TransactionNotFoundException;

import com.upgrad.payment.repository.TransactionRepository;
import com.upgrad.payment.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository repository;

    @Override
    public int makeTransaction(Transaction transaction) {

        String paymentMode = transaction.getPaymentMode().toUpperCase();

        Transaction savedTranscation;

        if (paymentMode.equals("UPI") || paymentMode.equals("CARD") )
        {

             savedTranscation = repository.save(transaction);

        }
        else {
            throw new InvalidPayMentOptionException("Invalid payment");
        }
        return  savedTranscation.getTransactionId();

    }

    @Override
    public List<Transaction> getAll() {
        return repository.findAll();
    }

    @Override
    public Transaction getTransactionById(int transcationId) {
        return repository.findTranscationBytransactionId(transcationId).orElseThrow(()->new TransactionNotFoundException("There is no transcation for this transaction id "+transcationId));

    }
}
