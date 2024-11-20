package com.huytran.cryptotrading.cryptotradingsystem.controller;

import com.huytran.cryptotrading.cryptotradingsystem.connector.feignclient.BinancePriceFeignClient;
import com.huytran.cryptotrading.cryptotradingsystem.connector.feignclient.HuobiPriceFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BaseController {

    private final BinancePriceFeignClient binancePriceFeignClient;
    private final HuobiPriceFeignClient huobiPriceFeignClient;

    @GetMapping("/prices/latest")
    public void fetchPrice() {
        System.out.println("Binance Price: " + binancePriceFeignClient.fetchBinancePair());
        System.out.println("Huobi Price: " + huobiPriceFeignClient.fetchHuobiPair());
    }
}
