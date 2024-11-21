package com.huytran.cryptotrading.cryptotradingsystem.connector.response;

import com.huytran.cryptotrading.cryptotradingsystem.connector.model.HuobiPair;
import java.util.List;
import lombok.*;

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
