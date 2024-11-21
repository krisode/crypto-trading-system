package com.huytran.cryptotrading.cryptotradingsystem.model.response;

import lombok.Data;

@Data
public class LatestBestPriceResponse {
  private String symbol;
  private Double bidPrice;
  private Double askPrice;
}
