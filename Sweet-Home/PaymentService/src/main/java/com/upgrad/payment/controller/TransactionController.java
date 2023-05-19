package com.upgrad.payment.controller;

import com.upgrad.payment.dto.TransactionDTO;
import com.upgrad.payment.entities.Transaction;
import com.upgrad.payment.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Integer> makeTransaction(@RequestBody TransactionDTO transactionDTO)
    {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);

        int transactionId = transactionService.makeTransaction(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionId);


    }
//
    @GetMapping(value = "/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransactionByTransactionId(@PathVariable(name = "transactionId") int transactionId)
    {
        Transaction transaction = transactionService.getTransactionById(transactionId);

        TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(transactionDTO);


    }


}
