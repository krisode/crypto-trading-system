package com.huytran.cryptotrading.cryptotradingsystem.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "crypto-trading-system.config.scheduler")
public class SchedulerConfig {

  private long priceAggregationInterval;
}
