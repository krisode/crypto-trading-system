package com.huytran.cryptotrading.cryptotradingsystem.service.provider;

import com.huytran.cryptotrading.cryptotradingsystem.config.TradingConfig;
import com.huytran.cryptotrading.cryptotradingsystem.connector.feignclient.HuobiPriceFeignClient;
import com.huytran.cryptotrading.cryptotradingsystem.entity.AggregatedPrice;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HuobiPriceProvider implements PriceProvider {
  private final TradingConfig tradingConfig;
  private final HuobiPriceFeignClient huobiPriceFeignClient;

  @Override
  public List<AggregatedPrice> fetchPrices() {
    return huobiPriceFeignClient.fetchHuobiPair().getData().stream()
        .filter(pair -> tradingConfig.isSupportedSymbol(pair.getSymbol()))
        .map(
            pair ->
                AggregatedPrice.builder()
                    .symbol(pair.getSymbol().toUpperCase())
                    .bidPrice(pair.getBid())
                    .askPrice(pair.getAsk())
                    .build())
        .toList();
  }
}
