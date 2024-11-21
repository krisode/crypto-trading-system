package com.huytran.cryptotrading.cryptotradingsystem.service;

import com.huytran.cryptotrading.cryptotradingsystem.entity.CryptoUser;
import java.util.Optional;

public interface CryptoUserService {

  Optional<CryptoUser> getByUserId(Long userId);
}
