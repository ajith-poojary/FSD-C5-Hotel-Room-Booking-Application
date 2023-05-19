package com.upgrad.payment.repository;

import com.upgrad.payment.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Optional<Transaction> findTranscationBytransactionId(int transactionId);
}
