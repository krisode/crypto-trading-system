package com.huytran.cryptotrading.cryptotradingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    private CryptoUser user;

    private String currency;

    private Double balance;

    public void add(double quantity) {
        this.balance += quantity;
    }

    public void subtract(double quantity) {
        if (this.balance < quantity) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance -= quantity;
    }
}
