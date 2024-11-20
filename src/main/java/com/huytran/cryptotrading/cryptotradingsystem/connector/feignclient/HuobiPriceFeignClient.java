package com.huytran.cryptotrading.cryptotradingsystem.connector.feignclient;

import com.huytran.cryptotrading.cryptotradingsystem.connector.response.HuobiPairResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "huobiFeignClient")
public interface HuobiPriceFeignClient {

    @GetMapping(value = "/market/tickers")
    HuobiPairResponse fetchHuobiPair();

    // TODO: Create a service to fetch and execute fetching logic
}
