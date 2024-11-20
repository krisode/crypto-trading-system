package com.huytran.cryptotrading.cryptotradingsystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    private CryptoUser user;

    private String symbol;

    @Column(name = "transaction_type")
    private String transactionType; // BUY or SELL

    private Double price;

    private Double quantity;

    private Double totalValue;

    @Column(name = "timestamp")
    @Builder.Default private LocalDateTime timestamp = LocalDateTime.now();
}
