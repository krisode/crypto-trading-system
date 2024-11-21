package com.huytran.cryptotrading.cryptotradingsystem.model.response;

import com.huytran.cryptotrading.cryptotradingsystem.enums.Symbol;
import com.huytran.cryptotrading.cryptotradingsystem.enums.TradeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeResponse {

  public TradeResponse(Symbol symbol, TradeType tradeType, double quantity, double price) {
    this.symbol = symbol;
    this.tradeType = tradeType;
    this.quantity = quantity;
    this.price = price;
  }

  private Symbol symbol;
  private TradeType tradeType;
  private double quantity;
  private double price;
  private double total;

  public double getTotal() {
    return quantity * price;
  }
}
