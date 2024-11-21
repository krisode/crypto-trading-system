package com.huytran.cryptotrading.cryptotradingsystem.service.action;

import com.huytran.cryptotrading.cryptotradingsystem.entity.CryptoUser;
import com.huytran.cryptotrading.cryptotradingsystem.entity.Transaction;
import com.huytran.cryptotrading.cryptotradingsystem.model.request.TradeRequest;
import com.huytran.cryptotrading.cryptotradingsystem.service.TransactionService;
import com.huytran.cryptotrading.cryptotradingsystem.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SellTradeAction implements TradeAction {

  private final WalletService walletService;
  private final TransactionService transactionService;

  @Override
  public void execute(CryptoUser cryptoUser, TradeRequest request, double tradePrice) {
    walletService.sellCrypto(cryptoUser, request.getSymbol(), tradePrice, request.getQuantity());
    transactionService.createTransaction(
        Transaction.initSellTransactionInstance(
            cryptoUser, request.getSymbol().name(), tradePrice, request.getQuantity()));
  }
}
