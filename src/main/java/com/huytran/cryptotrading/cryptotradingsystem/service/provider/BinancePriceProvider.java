package com.huytran.cryptotrading.cryptotradingsystem.service.provider;

import com.huytran.cryptotrading.cryptotradingsystem.config.TradingConfig;
import com.huytran.cryptotrading.cryptotradingsystem.connector.feignclient.BinancePriceFeignClient;
import com.huytran.cryptotrading.cryptotradingsystem.entity.AggregatedPrice;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BinancePriceProvider implements PriceProvider {
  private final TradingConfig tradingConfig;
  private final BinancePriceFeignClient binancePriceFeignClient;

  @Override
  public List<AggregatedPrice> fetchPrices() {
    return binancePriceFeignClient.fetchBinancePair().stream()
        .filter(pair -> tradingConfig.isSupportedSymbol(pair.getSymbol()))
        .map(
            pair ->
                AggregatedPrice.builder()
                    .symbol(pair.getSymbol().toUpperCase())
                    .bidPrice(pair.getBidPrice())
                    .askPrice(pair.getAskPrice())
                    .build())
        .toList();
  }
}
