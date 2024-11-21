package com.huytran.cryptotrading.cryptotradingsystem.repository;

import com.huytran.cryptotrading.cryptotradingsystem.entity.Wallet;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
  Optional<Wallet> findByUserIdAndCurrency(Long userId, String currency);

  List<Wallet> findAllByUserId(Long userId);
}
