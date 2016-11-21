package com.savior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savior.model.security.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
