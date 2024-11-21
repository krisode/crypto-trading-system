package com.huytran.cryptotrading.cryptotradingsystem.service;

import com.huytran.cryptotrading.cryptotradingsystem.model.request.TradeRequest;
import com.huytran.cryptotrading.cryptotradingsystem.model.response.TradeResponse;

public interface TradeService {

    TradeResponse executeTrade(TradeRequest tradeRequest);
}
