package com.huytran.cryptotrading.cryptotradingsystem.service;

import com.huytran.cryptotrading.cryptotradingsystem.entity.CryptoUser;
import com.huytran.cryptotrading.cryptotradingsystem.entity.Wallet;
import com.huytran.cryptotrading.cryptotradingsystem.enums.Currency;
import com.huytran.cryptotrading.cryptotradingsystem.enums.Symbol;
import com.huytran.cryptotrading.cryptotradingsystem.enums.TradeType;
import java.util.List;

public interface WalletService {

  Wallet initWallet(CryptoUser cryptoUser, Currency currency);

  Wallet requireWalletByUserIdAndCurrency(Long userId, Currency currency);

  List<Wallet> getAllWalletsByUserId(Long userId);

  void buyCrypto(
      CryptoUser cryptoUser,
      Symbol symbol,
      TradeType tradeType,
      double tradePrice,
      double quantity);

  void sellCrypto(
      CryptoUser cryptoUser,
      Symbol symbol,
      TradeType tradeType,
      double tradePrice,
      double quantity);
}
