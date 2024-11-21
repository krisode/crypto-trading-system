package com.huytran.cryptotrading.cryptotradingsystem.service.provider;

import com.huytran.cryptotrading.cryptotradingsystem.connector.feignclient.BinancePriceFeignClient;
import com.huytran.cryptotrading.cryptotradingsystem.entity.AggregatedPrice;
import com.huytran.cryptotrading.cryptotradingsystem.enums.Symbol;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BinancePriceProvider implements PriceProvider {
  private final BinancePriceFeignClient binancePriceFeignClient;

  @Override
  public List<AggregatedPrice> fetchPrices() {
    return binancePriceFeignClient.fetchBinancePair().stream()
        .filter(pair -> isSupportedSymbol(pair.getSymbol()))
        .map(
            pair ->
                AggregatedPrice.builder()
                    .symbol(pair.getSymbol().toUpperCase())
                    .bidPrice(pair.getBidPrice())
                    .askPrice(pair.getAskPrice())
                    .build())
        .toList();
  }

  private boolean isSupportedSymbol(String symbol) {
    return Symbol.ETHUSDT.name().equalsIgnoreCase(symbol)
        || Symbol.BTCUSDT.name().equalsIgnoreCase(symbol);
  }
}
