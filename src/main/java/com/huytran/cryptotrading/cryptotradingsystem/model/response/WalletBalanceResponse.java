package com.huytran.cryptotrading.cryptotradingsystem.model.response;

import com.huytran.cryptotrading.cryptotradingsystem.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletBalanceResponse {
    Currency currency;
    double balance;
}
