package com.huytran.cryptotrading.cryptotradingsystem.service.factory;

import com.huytran.cryptotrading.cryptotradingsystem.entity.AggregatedPrice;
import com.huytran.cryptotrading.cryptotradingsystem.service.provider.PriceProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceProviderFactory {
  private final List<PriceProvider> priceProviders;

  public List<AggregatedPrice> fetchAllPrices() {
    return priceProviders.stream()
        .flatMap(priceProvider -> priceProvider.fetchPrices().stream())
        .toList();
  }
}
