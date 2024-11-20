package com.huytran.cryptotrading.cryptotradingsystem.service;

import com.huytran.cryptotrading.cryptotradingsystem.connector.feignclient.BinancePriceFeignClient;
import com.huytran.cryptotrading.cryptotradingsystem.connector.feignclient.HuobiPriceFeignClient;
import com.huytran.cryptotrading.cryptotradingsystem.entity.AggregatedPrice;
import com.huytran.cryptotrading.cryptotradingsystem.enums.CryptoPair;
import com.huytran.cryptotrading.cryptotradingsystem.repository.AggregatedPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AggregatedPriceServiceImpl implements AggregatedPriceService {

    private final BinancePriceFeignClient binancePriceFeignClient;
    private final HuobiPriceFeignClient huobiPriceFeignClient;

    private final AggregatedPriceRepository aggregatedPriceRepository;

    @Override
    public void aggregatePrice() {
        // TODO: Extract logic to fetch Binance & Huobi with desired CryptoPair
        final var binancePairs = binancePriceFeignClient.fetchBinancePair();
        final var huobiPairs = huobiPriceFeignClient.fetchHuobiPair().getData();

        final var aggregatedPrices = Stream.concat(
                binancePairs.stream()
                        .filter(binancePair -> CryptoPair.ETHUSDT.name().equalsIgnoreCase(binancePair.getSymbol())
                                            || CryptoPair.BTCUSDT.name().equalsIgnoreCase(binancePair.getSymbol()))
                        .map(binancePair -> AggregatedPrice.builder().pair(binancePair.getSymbol().toUpperCase()).bidPrice(binancePair.getBidPrice()).askPrice(binancePair.getAskPrice()).build()),
                huobiPairs.stream()
                        .filter(binancePair -> CryptoPair.ETHUSDT.name().equalsIgnoreCase(binancePair.getSymbol())
                                || CryptoPair.BTCUSDT.name().equalsIgnoreCase(binancePair.getSymbol()))
                        .map(binancePair -> AggregatedPrice.builder().pair(binancePair.getSymbol().toUpperCase()).bidPrice(binancePair.getBid()).askPrice(binancePair.getAsk()).build())
                ).toList();

        Map<String, AggregatedPrice> bestPrices = aggregatedPrices.stream()
                .collect(Collectors.toMap(
                   AggregatedPrice::getPair,
                   p -> p,
                   (p1 , p2) ->
                       AggregatedPrice.builder()
                               .pair(p1.getPair())
                               .bidPrice(Math.max(p1.getBidPrice(), p2.getBidPrice()))
                               .askPrice(Math.min(p1.getAskPrice(), p2.getAskPrice()))
                               .build()

                ));
        bestPrices.values().forEach(this::saveToDb);
    }

    private void saveToDb(AggregatedPrice aggregatedPrice) {
        aggregatedPriceRepository.save(aggregatedPrice);
    }
}
