package com.huytran.cryptotrading.cryptotradingsystem.controller;

import com.huytran.cryptotrading.cryptotradingsystem.connector.feignclient.BinancePriceFeignClient;
import com.huytran.cryptotrading.cryptotradingsystem.connector.feignclient.HuobiPriceFeignClient;
import com.huytran.cryptotrading.cryptotradingsystem.service.AggregatedPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BaseController {

    private final AggregatedPriceService aggregatedPriceService;

    @GetMapping("/prices/latest")
    public void fetchPrice() {
        aggregatedPriceService.aggregatePrice();
    }
}
