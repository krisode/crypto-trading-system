package com.huytran.cryptotrading.cryptotradingsystem.service.provider;

import com.huytran.cryptotrading.cryptotradingsystem.entity.AggregatedPrice;
import java.util.List;

public interface PriceProvider {
  List<AggregatedPrice> fetchPrices();
}
