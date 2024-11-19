package com.huytran.cryptotrading.cryptotradingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "aggregated_price")
public class AggregatedPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pair;
    private Double bidPrice; // Best price to SELL
    private Double askPrice; // Best price to BUY

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // Constructors, Getters, and Setters
}
