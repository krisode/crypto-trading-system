package com.huytran.cryptotrading.cryptotradingsystem.controller;

import com.huytran.cryptotrading.cryptotradingsystem.entity.AggregatedPrice;
import com.huytran.cryptotrading.cryptotradingsystem.enums.Symbol;
import com.huytran.cryptotrading.cryptotradingsystem.service.AggregatedPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class PriceController {

    private final AggregatedPriceService aggregatedPriceService;

    @GetMapping("/latest")
    public ResponseEntity<List<AggregatedPrice>> getLatestBestPrices() {
        return ResponseEntity.ok(aggregatedPriceService.getLatestBestPriceForAllSymbols());
    }

    @GetMapping("/latest/{symbol}")
    public ResponseEntity<AggregatedPrice> getLatestBestPrice(@PathVariable Symbol symbol) {
        return ResponseEntity.ok(aggregatedPriceService.getLatestBestPriceForSymbol(symbol));
    }
}
