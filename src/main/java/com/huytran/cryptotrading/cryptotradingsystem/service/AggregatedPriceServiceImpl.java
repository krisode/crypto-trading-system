package com.huytran.cryptotrading.cryptotradingsystem.service;

import com.huytran.cryptotrading.cryptotradingsystem.entity.AggregatedPrice;
import com.huytran.cryptotrading.cryptotradingsystem.enums.Symbol;
import com.huytran.cryptotrading.cryptotradingsystem.repository.AggregatedPriceRepository;
import com.huytran.cryptotrading.cryptotradingsystem.service.factory.PriceProviderFactory;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AggregatedPriceServiceImpl implements AggregatedPriceService {

  private final PriceProviderFactory priceProviderFactory;
  private final AggregatedPriceRepository aggregatedPriceRepository;

  @Override
  public void aggregatePrice() {
    final var allPrices = priceProviderFactory.fetchAllPrices();
    final var bestPrices = calculateBestPrices(allPrices);
    aggregatedPriceRepository.saveAll(bestPrices.values());
  }

  @Override
  public AggregatedPrice requireLatestBestPriceForSymbol(Symbol symbol) {
    return aggregatedPriceRepository
        .findTop1AggregatedPriceBySymbolOrderByTimestampDesc(symbol.name())
        .orElseThrow(() -> new RuntimeException("No aggregated price found for symbol: " + symbol));
  }

  @Override
  public List<AggregatedPrice> getLatestBestPriceForAllSymbols() {
    return aggregatedPriceRepository.findAllGroupedBySymbolOrderByTimestampDesc();
  }

  private Map<String, AggregatedPrice> calculateBestPrices(List<AggregatedPrice> aggregatedPrices) {
    return aggregatedPrices.stream()
        .collect(Collectors.toMap(AggregatedPrice::getSymbol, price -> price, this::mergePrices));
  }

  private AggregatedPrice mergePrices(AggregatedPrice price1, AggregatedPrice price2) {
    return AggregatedPrice.builder()
        .symbol(price1.getSymbol())
        .bidPrice(Math.max(price1.getBidPrice(), price2.getBidPrice()))
        .askPrice(Math.min(price1.getAskPrice(), price2.getAskPrice()))
        .build();
  }
}
