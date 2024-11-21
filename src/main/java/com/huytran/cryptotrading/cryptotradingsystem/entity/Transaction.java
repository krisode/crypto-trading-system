package com.huytran.cryptotrading.cryptotradingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huytran.cryptotrading.cryptotradingsystem.enums.TradeType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

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

  @ManyToOne private CryptoUser user;

  private String symbol;

  private String type; // BUY or SELL

  private Double price;

  private Double quantity;

  @Column(name = "timestamp")
  @Builder.Default
  private LocalDateTime timestamp = LocalDateTime.now();

  public static Transaction initBuyTransactionInstance(
      CryptoUser cryptoUser, String symbol, double price, double quantity) {
    return Transaction.builder()
        .user(cryptoUser)
        .symbol(symbol)
        .type(TradeType.BUY.name())
        .price(price)
        .quantity(quantity)
        .timestamp(LocalDateTime.now())
        .build();
  }

  public static Transaction initSellTransactionInstance(
      CryptoUser cryptoUser, String symbol, double price, double quantity) {
    return Transaction.builder()
        .user(cryptoUser)
        .symbol(symbol)
        .type(TradeType.SELL.name())
        .price(price)
        .quantity(quantity)
        .timestamp(LocalDateTime.now())
        .build();
  }
}
