package com.huytran.cryptotrading.cryptotradingsystem.service;

import com.huytran.cryptotrading.cryptotradingsystem.entity.AggregatedPrice;
import com.huytran.cryptotrading.cryptotradingsystem.enums.Symbol;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AggregatedPriceService {

    void aggregatePrice();

    AggregatedPrice getLatestBestPriceForSymbol(Symbol symbol);

    List<AggregatedPrice> getLatestBestPriceForAllSymbols();
}
