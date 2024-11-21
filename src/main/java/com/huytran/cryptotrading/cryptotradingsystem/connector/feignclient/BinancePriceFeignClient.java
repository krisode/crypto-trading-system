package com.huytran.cryptotrading.cryptotradingsystem.connector.feignclient;

import com.huytran.cryptotrading.cryptotradingsystem.connector.model.BinancePair;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "binanceFeignClient")
public interface BinancePriceFeignClient {

  @GetMapping(value = "/api/v3/ticker/bookTicker")
  List<BinancePair> fetchBinancePair();
}
