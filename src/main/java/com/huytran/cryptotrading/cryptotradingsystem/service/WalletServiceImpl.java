package com.huytran.cryptotrading.cryptotradingsystem.service;

import com.huytran.cryptotrading.cryptotradingsystem.entity.CryptoUser;
import com.huytran.cryptotrading.cryptotradingsystem.entity.Transaction;
import com.huytran.cryptotrading.cryptotradingsystem.entity.Wallet;
import com.huytran.cryptotrading.cryptotradingsystem.enums.Currency;
import com.huytran.cryptotrading.cryptotradingsystem.enums.Symbol;
import com.huytran.cryptotrading.cryptotradingsystem.enums.TradeType;
import com.huytran.cryptotrading.cryptotradingsystem.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final TransactionService transactionService;
    private final WalletRepository walletRepository;

    @Override
    public Wallet initWallet(CryptoUser cryptoUser, Currency currency) {
        final var wallet = Wallet.builder()
                .user(cryptoUser)
                .currency(currency.name())
                .balance(0.0)
                .build();

        return walletRepository.save(wallet);
    }

    @Override
    public Optional<Wallet> getWalletByUserAndCurrency(CryptoUser cryptoUser, Currency currency) {
        return walletRepository.findByUserIdAndCurrency(cryptoUser.getId(), currency.name());
    }

    @Override
    public void buyCrypto(CryptoUser cryptoUser, Symbol symbol, TradeType tradeType, double tradePrice, double quantity) {
        final var baseCurrency = Symbol.getBaseCurrency(symbol);
        final var quoteCurrency = Symbol.getQuoteCurrency(symbol);
        final var baseCurrencyWallet = getOrCreateWallet(cryptoUser, Currency.valueOf(baseCurrency));
        final var quoteCurrencyWallet = getOrCreateWallet(cryptoUser, Currency.valueOf(quoteCurrency));
        final var total = tradePrice * quantity;

        quoteCurrencyWallet.subtract(total);
        baseCurrencyWallet.add(quantity);

        walletRepository.save(baseCurrencyWallet);
        walletRepository.save(quoteCurrencyWallet);
        transactionService.createTransaction(Transaction.initBuyTransactionInstance(cryptoUser, symbol.name(), tradePrice, quantity));
    }

    @Override
    public void sellCrypto(CryptoUser cryptoUser, Symbol symbol, TradeType tradeType, double tradePrice, double quantity) {
        final var baseCurrency = Symbol.getBaseCurrency(symbol);
        final var quoteCurrency = Symbol.getQuoteCurrency(symbol);
        final var baseCurrencyWallet = getOrCreateWallet(cryptoUser, Currency.valueOf(baseCurrency));
        final var quoteCurrencyWallet = getOrCreateWallet(cryptoUser, Currency.valueOf(quoteCurrency));
        final var total = tradePrice * quantity;

        baseCurrencyWallet.subtract(quantity);
        quoteCurrencyWallet.add(total);

        walletRepository.save(baseCurrencyWallet);
        walletRepository.save(quoteCurrencyWallet);
        transactionService.createTransaction(Transaction.initSellTransactionInstance(cryptoUser, symbol.name(), tradePrice, quantity));
    }

    private Wallet getOrCreateWallet(CryptoUser cryptoUser, Currency currency) {
        return walletRepository.findByUserIdAndCurrency(cryptoUser.getId(), currency.name())
                .orElseGet(() -> initWallet(cryptoUser, currency));
    }
}
