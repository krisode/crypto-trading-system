package com.huytran.cryptotrading.cryptotradingsystem.service;

import com.huytran.cryptotrading.cryptotradingsystem.entity.AggregatedPrice;
import com.huytran.cryptotrading.cryptotradingsystem.enums.Symbol;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface AggregatedPriceService {

  void aggregatePrice();

  AggregatedPrice requireLatestBestPriceForSymbol(Symbol symbol);

  List<AggregatedPrice> getLatestBestPriceForAllSymbols();
}
