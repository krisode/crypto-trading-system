package com.huytran.cryptotrading.cryptotradingsystem.model.response;

import com.huytran.cryptotrading.cryptotradingsystem.enums.TradeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeHistoryResponse {
    private String symbol;
    private TradeType type;
    private double price;
    private double quantity;
    private double total;
    private LocalDateTime timestamp;

    public double getTotal() {
        return price * quantity;
    }
}
