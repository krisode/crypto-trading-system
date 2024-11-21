package com.huytran.cryptotrading.cryptotradingsystem.service;

import com.huytran.cryptotrading.cryptotradingsystem.entity.CryptoUser;
import com.huytran.cryptotrading.cryptotradingsystem.repository.CryptoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CryptoUserServiceImpl implements CryptoUserService {

    private final CryptoUserRepository cryptoUserRepository;

    @Override
    public Optional<CryptoUser> getByUserId(Long userId) {
        return cryptoUserRepository.findById(userId);
    }
}
