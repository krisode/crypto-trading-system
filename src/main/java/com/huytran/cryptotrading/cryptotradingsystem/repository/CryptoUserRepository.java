package com.huytran.cryptotrading.cryptotradingsystem.repository;

import com.huytran.cryptotrading.cryptotradingsystem.entity.CryptoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoUserRepository extends JpaRepository<CryptoUser, Long> {
}
