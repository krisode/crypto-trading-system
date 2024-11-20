package com.huytran.cryptotrading.cryptotradingsystem.scheduler;

import com.huytran.cryptotrading.cryptotradingsystem.service.AggregatedPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final AggregatedPriceService aggregatedPriceService;

    @Scheduled(fixedRateString = "#{schedulerConfig.priceAggregationInterval}") // SpEL expression
    public void aggregatePrice() {
        aggregatedPriceService.aggregatePrice();
    }
}
