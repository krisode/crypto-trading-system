package com.huytran.cryptotrading.cryptotradingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "trading_pair")
public class TradingPair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pair; // e.g., BTCUSDT or ETHUSDT

    // Getters and Setters
}
