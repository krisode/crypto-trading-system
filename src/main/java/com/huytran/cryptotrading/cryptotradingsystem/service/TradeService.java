package com.huytran.cryptotrading.cryptotradingsystem.service;

import com.huytran.cryptotrading.cryptotradingsystem.entity.Transaction;
import com.huytran.cryptotrading.cryptotradingsystem.model.request.TradeRequest;
import com.huytran.cryptotrading.cryptotradingsystem.model.response.TradeResponse;

import java.util.List;

public interface TradeService {

    TradeResponse executeTrade(TradeRequest tradeRequest);

    List<Transaction> getTradeHistoryByUserId(Long userId);
}
