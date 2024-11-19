package com.huytran.cryptotrading.cryptotradingsystem.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CryptoUser user;

    private String pair;

    @Column(name = "transaction_type")
    private String transactionType; // BUY or SELL
    private Double price;
    private Double quantity;
    private Double totalValue;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // Constructors, Getters, and Setters
}
