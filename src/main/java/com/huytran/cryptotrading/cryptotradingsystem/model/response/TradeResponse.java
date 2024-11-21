package com.huytran.cryptotrading.cryptotradingsystem.model.response;

import com.huytran.cryptotrading.cryptotradingsystem.enums.Symbol;
import com.huytran.cryptotrading.cryptotradingsystem.enums.TradeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeResponse {
  private Symbol symbol;
  private TradeType tradeType;
  private double quantity;
  private double price;
  private double total;

  public double getTotal() {
    return quantity * price;
  }
}
