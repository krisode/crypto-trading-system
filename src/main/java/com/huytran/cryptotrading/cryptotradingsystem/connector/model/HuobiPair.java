package com.huytran.cryptotrading.cryptotradingsystem.connector.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HuobiPair {
    private String symbol;
    private double open;
    private double high;
    private double low;
    private double close;
    private double quantity;
    private double vol;
    private long count;
    private double bid;
    private double bidSize;
    private double ask;
    private double askSize;
}
