package com.upgrad.payment.service;

import com.upgrad.payment.entities.Transaction;

import java.util.List;

public interface TransactionService {

   public int makeTransaction(Transaction transaction);

  public List<Transaction> getAll();

  public Transaction getTransactionById(int transcationId);
}
