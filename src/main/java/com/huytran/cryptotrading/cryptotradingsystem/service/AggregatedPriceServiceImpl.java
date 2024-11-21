package com.huytran.cryptotrading.cryptotradingsystem.service;

import com.huytran.cryptotrading.cryptotradingsystem.connector.feignclient.BinancePriceFeignClient;
import com.huytran.cryptotrading.cryptotradingsystem.connector.feignclient.HuobiPriceFeignClient;
import com.huytran.cryptotrading.cryptotradingsystem.entity.AggregatedPrice;
import com.huytran.cryptotrading.cryptotradingsystem.enums.Symbol;
import com.huytran.cryptotrading.cryptotradingsystem.repository.AggregatedPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
                        .filter(binancePair -> Symbol.ETHUSDT.name().equalsIgnoreCase(binancePair.getSymbol())
                                            || Symbol.BTCUSDT.name().equalsIgnoreCase(binancePair.getSymbol()))
                        .map(binancePair -> AggregatedPrice.builder().symbol(binancePair.getSymbol().toUpperCase()).bidPrice(binancePair.getBidPrice()).askPrice(binancePair.getAskPrice()).build()),
                huobiPairs.stream()
                        .filter(binancePair -> Symbol.ETHUSDT.name().equalsIgnoreCase(binancePair.getSymbol())
                                || Symbol.BTCUSDT.name().equalsIgnoreCase(binancePair.getSymbol()))
                        .map(binancePair -> AggregatedPrice.builder().symbol(binancePair.getSymbol().toUpperCase()).bidPrice(binancePair.getBid()).askPrice(binancePair.getAsk()).build())
                ).toList();

        Map<String, AggregatedPrice> bestPrices = aggregatedPrices.stream()
                .collect(Collectors.toMap(
                   AggregatedPrice::getSymbol,
                   p -> p,
                   (p1 , p2) ->
                       AggregatedPrice.builder()
                               .symbol(p1.getSymbol())
                               .bidPrice(Math.min(p1.getBidPrice(), p2.getBidPrice()))
                               .askPrice(Math.max(p1.getAskPrice(), p2.getAskPrice()))
                               .build()

                ));
        bestPrices.values().forEach(this::saveToDb);
    }

    @Override
    public AggregatedPrice getLatestBestPriceForSymbol(Symbol symbol) {
        final var latestBestPrice = aggregatedPriceRepository.findTop1AggregatedPriceBySymbolOrderByTimestampDesc(symbol.name());

        if (latestBestPrice.isEmpty()) {
            throw new RuntimeException("No aggregated price found for symbol: " + symbol);
        }

        return latestBestPrice.get();
    }

    /**
     * @return
     */
    @Override
    public List<AggregatedPrice> getLatestBestPriceForAllSymbols() {
        return aggregatedPriceRepository.findAllGroupedBySymbolOrderByTimestampDesc();
    }

    private void saveToDb(AggregatedPrice aggregatedPrice) {
        aggregatedPriceRepository.save(aggregatedPrice);
    }
}
