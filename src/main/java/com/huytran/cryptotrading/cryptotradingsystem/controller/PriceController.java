package com.huytran.cryptotrading.cryptotradingsystem.controller;

import com.huytran.cryptotrading.cryptotradingsystem.enums.Symbol;
import com.huytran.cryptotrading.cryptotradingsystem.mapper.PriceMapper;
import com.huytran.cryptotrading.cryptotradingsystem.model.response.LatestBestPriceResponse;
import com.huytran.cryptotrading.cryptotradingsystem.service.AggregatedPriceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class PriceController {

  private final PriceMapper priceMapper;
  private final AggregatedPriceService aggregatedPriceService;

  @GetMapping("/latest")
  public ResponseEntity<List<LatestBestPriceResponse>> getLatestBestPrices() {
    final var latestBestPriceForAllSymbols =
        aggregatedPriceService.getLatestBestPriceForAllSymbols();
    final var response = priceMapper.toResponse(latestBestPriceForAllSymbols);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/latest/{symbol}")
  public ResponseEntity<LatestBestPriceResponse> getLatestBestPrice(@PathVariable Symbol symbol) {
    final var latestBestPriceForSymbol =
        aggregatedPriceService.requireLatestBestPriceForSymbol(symbol);
    final var response = priceMapper.toResponse(latestBestPriceForSymbol);

    return ResponseEntity.ok(response);
  }
}
