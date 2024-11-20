package com.huytran.cryptotrading.cryptotradingsystem.model.response;

import lombok.Data;

@Data
public class LatestBestPrice {
    private String symbol;
    private Double bidPrice;
    private Double askPrice;
}
