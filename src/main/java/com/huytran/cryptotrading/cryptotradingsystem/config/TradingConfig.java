package com.huytran.cryptotrading.cryptotradingsystem.config;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "crypto-trading-system.config.trading")
public class TradingConfig {
  private List<String> supportedSymbols;

  public boolean isSupportedSymbol(String symbol) {
    return supportedSymbols.contains(symbol);
  }
}
