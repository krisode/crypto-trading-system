package com.huytran.cryptotrading.cryptotradingsystem.enums;

public enum Symbol {
  ETHUSDT,
  BTCUSDT;

  public static String getBaseCurrency(Symbol symbol) {
    if (symbol == null) {
      throw new IllegalArgumentException("symbol cannot be null");
    }

    return switch (symbol) {
      case ETHUSDT -> "ETH";
      case BTCUSDT -> "BTC";
    };
  }

  public static String getQuoteCurrency(Symbol symbol) {
    if (symbol == null) {
      throw new IllegalArgumentException("symbol cannot be null");
    }

    return switch (symbol) {
      case ETHUSDT, BTCUSDT -> "USDT";
    };
  }
}
