package com.huytran.cryptotrading.cryptotradingsystem.connector.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BinancePair {
  private String symbol;
  private double bidPrice;
  private double bidQty;
  private double askPrice;
  private double askQty;
}
