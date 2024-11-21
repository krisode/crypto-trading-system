package com.huytran.cryptotrading.cryptotradingsystem.controller;

import com.huytran.cryptotrading.cryptotradingsystem.mapper.TradeMapper;
import com.huytran.cryptotrading.cryptotradingsystem.model.request.TradeRequest;
import com.huytran.cryptotrading.cryptotradingsystem.model.response.TradeHistoryResponse;
import com.huytran.cryptotrading.cryptotradingsystem.model.response.TradeResponse;
import com.huytran.cryptotrading.cryptotradingsystem.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trades")
@RequiredArgsConstructor
public class TradeController {

    private static final Long DEFAULT_USER_ID = 1L;

    private final TradeMapper tradeMapper;
    private final TradeService tradeService;

    @PostMapping
    public ResponseEntity<TradeResponse> trade(@RequestBody TradeRequest tradeRequest) {
        tradeRequest.setCryptoUserId(DEFAULT_USER_ID);
        final var tradeResponse = tradeService.executeTrade(tradeRequest);

        return ResponseEntity.ok(tradeResponse);
    }

    @GetMapping("/history")
    public ResponseEntity<List<TradeHistoryResponse>> getTradeHistory() {
        final var tradeHistory = tradeService.getTradeHistoryByUserId(DEFAULT_USER_ID);
        final var response = tradeMapper.toResponse(tradeHistory);

        return ResponseEntity.ok(response);
    }
}
