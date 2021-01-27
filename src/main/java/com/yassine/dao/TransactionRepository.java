package com.yassine.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yassine.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
