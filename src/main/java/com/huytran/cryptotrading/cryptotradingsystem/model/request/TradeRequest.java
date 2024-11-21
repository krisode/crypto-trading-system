package com.huytran.cryptotrading.cryptotradingsystem.model.request;

import com.huytran.cryptotrading.cryptotradingsystem.enums.Symbol;
import com.huytran.cryptotrading.cryptotradingsystem.enums.TradeType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TradeRequest {
    @Builder.Default private Long cryptoUserId = 1L;
    private Symbol symbol;
    private TradeType tradeType;
    private double quantity;
}
