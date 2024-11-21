package com.huytran.cryptotrading.cryptotradingsystem.controller;

import com.huytran.cryptotrading.cryptotradingsystem.enums.Currency;
import com.huytran.cryptotrading.cryptotradingsystem.mapper.WalletMapper;
import com.huytran.cryptotrading.cryptotradingsystem.model.response.WalletBalanceResponse;
import com.huytran.cryptotrading.cryptotradingsystem.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private static final Long DEFAULT_USER_ID = 1L;

    private final WalletMapper walletMapper;
    private final WalletService walletService;

    @GetMapping("/balance")
    public ResponseEntity<List<WalletBalanceResponse>> getWallets() {
        final var wallets = walletService.getAllWalletsByUserId(DEFAULT_USER_ID);
        final var response = walletMapper.toResponse(wallets);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/balance/{currency}")
    public ResponseEntity<WalletBalanceResponse> getBalance(@PathVariable Currency currency) {
        final var wallet = walletService.requireWalletByUserIdAndCurrency(DEFAULT_USER_ID, currency);
        final var response = walletMapper.toResponse(wallet);

        return ResponseEntity.ok(response);
    }
}
