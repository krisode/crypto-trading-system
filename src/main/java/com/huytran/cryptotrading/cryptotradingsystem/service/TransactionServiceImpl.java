package com.huytran.cryptotrading.cryptotradingsystem.service;

import com.huytran.cryptotrading.cryptotradingsystem.entity.Transaction;
import com.huytran.cryptotrading.cryptotradingsystem.repository.TransactionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;

  @Override
  public Transaction createTransaction(Transaction transaction) {
    return transactionRepository.save(transaction);
  }

  @Override
  public List<Transaction> getAllTransactionsByUserId(Long userId) {
    return transactionRepository.findAllByUserId(userId);
  }
}
