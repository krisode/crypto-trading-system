package com.huytran.cryptotrading.cryptotradingsystem.service.action;

import com.huytran.cryptotrading.cryptotradingsystem.entity.CryptoUser;
import com.huytran.cryptotrading.cryptotradingsystem.model.request.TradeRequest;

public interface TradeAction {
  void execute(CryptoUser cryptoUser, TradeRequest request, double tradePrice);
}
