package com.huytran.cryptotrading.cryptotradingsystem.repository;

import com.huytran.cryptotrading.cryptotradingsystem.entity.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  List<Transaction> findAllByUserId(Long userId);
}
