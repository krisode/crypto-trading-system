package com.huytran.cryptotrading.cryptotradingsystem.service.factory;

import com.huytran.cryptotrading.cryptotradingsystem.enums.TradeType;
import com.huytran.cryptotrading.cryptotradingsystem.service.action.BuyTradeAction;
import com.huytran.cryptotrading.cryptotradingsystem.service.action.SellTradeAction;
import com.huytran.cryptotrading.cryptotradingsystem.service.action.TradeAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TradeActionFactory {

  private final BuyTradeAction buyTradeAction;
  private final SellTradeAction sellTradeAction;

  public TradeAction getTradeAction(TradeType tradeType) {
    return switch (tradeType) {
      case BUY -> buyTradeAction;
      case SELL -> sellTradeAction;
    };
  }
}
