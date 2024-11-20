package com.huytran.cryptotrading.cryptotradingsystem.connector.response;

import com.huytran.cryptotrading.cryptotradingsystem.connector.model.BinancePair;
import com.huytran.cryptotrading.cryptotradingsystem.connector.model.HuobiPair;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HuobiPairResponse {

    private List<HuobiPair> data;
    private String status;
    private Long ts;
}
