package com.huytran.cryptotrading.cryptotradingsystem.entity;

import com.huytran.cryptotrading.cryptotradingsystem.enums.TradeType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

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

  private String symbol;
  private Double bidPrice; // Best price to SELL
  private Double askPrice; // Best price to BUY
  @Builder.Default private LocalDateTime timestamp = LocalDateTime.now();

  public double getPriceForTradeType(TradeType tradeType) {
    if (TradeType.BUY.equals(tradeType)) {
      return this.bidPrice;
    } else {
      return this.askPrice;
    }
  }
}
