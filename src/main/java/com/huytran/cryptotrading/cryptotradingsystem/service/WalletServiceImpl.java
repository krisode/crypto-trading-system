package com.huytran.cryptotrading.cryptotradingsystem.service;

import com.huytran.cryptotrading.cryptotradingsystem.entity.CryptoUser;
import com.huytran.cryptotrading.cryptotradingsystem.entity.Wallet;
import com.huytran.cryptotrading.cryptotradingsystem.enums.Currency;
import com.huytran.cryptotrading.cryptotradingsystem.enums.Symbol;
import com.huytran.cryptotrading.cryptotradingsystem.repository.WalletRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

  private final WalletRepository walletRepository;

  @Override
  public Wallet initWallet(CryptoUser cryptoUser, Currency currency) {
    final var wallet =
        Wallet.builder().user(cryptoUser).currency(currency.name()).balance(0.0).build();

    return walletRepository.save(wallet);
  }

  @Override
  public Wallet requireWalletByUserIdAndCurrency(Long userId, Currency currency) {
    return walletRepository
        .findByUserIdAndCurrency(userId, currency.name())
        .orElseThrow(() -> new RuntimeException("Wallet not found"));
  }

  @Override
  public List<Wallet> getAllWalletsByUserId(Long userId) {
    return walletRepository.findAllByUserId(userId);
  }

  @Override
  public void buyCrypto(CryptoUser cryptoUser, Symbol symbol, double tradePrice, double quantity) {
    final var baseCurrency = Symbol.getBaseCurrency(symbol);
    final var quoteCurrency = Symbol.getQuoteCurrency(symbol);
    final var baseCurrencyWallet = getOrCreateWallet(cryptoUser, Currency.valueOf(baseCurrency));
    final var quoteCurrencyWallet = getOrCreateWallet(cryptoUser, Currency.valueOf(quoteCurrency));
    final var total = tradePrice * quantity;

    quoteCurrencyWallet.subtract(total);
    baseCurrencyWallet.add(quantity);

    walletRepository.saveAll(List.of(baseCurrencyWallet, quoteCurrencyWallet));
  }

  @Override
  public void sellCrypto(CryptoUser cryptoUser, Symbol symbol, double tradePrice, double quantity) {
    final var baseCurrency = Symbol.getBaseCurrency(symbol);
    final var quoteCurrency = Symbol.getQuoteCurrency(symbol);
    final var baseCurrencyWallet = getOrCreateWallet(cryptoUser, Currency.valueOf(baseCurrency));
    final var quoteCurrencyWallet = getOrCreateWallet(cryptoUser, Currency.valueOf(quoteCurrency));
    final var total = tradePrice * quantity;

    baseCurrencyWallet.subtract(quantity);
    quoteCurrencyWallet.add(total);

    walletRepository.saveAll(List.of(baseCurrencyWallet, quoteCurrencyWallet));
  }

  private Wallet getOrCreateWallet(CryptoUser cryptoUser, Currency currency) {
    return walletRepository
        .findByUserIdAndCurrency(cryptoUser.getId(), currency.name())
        .orElseGet(() -> initWallet(cryptoUser, currency));
  }
}
