package com.huytran.cryptotrading.cryptotradingsystem.service;

import com.huytran.cryptotrading.cryptotradingsystem.entity.Transaction;
import com.huytran.cryptotrading.cryptotradingsystem.model.request.TradeRequest;
import com.huytran.cryptotrading.cryptotradingsystem.model.response.TradeResponse;
import com.huytran.cryptotrading.cryptotradingsystem.service.factory.TradeActionFactory;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
  // TODO: Consider about Assumption
  // 1. User has already authenticated and authorised to access the APIs => This one
  // 2. User's initial wallet balance 50,000 USDT in DB record.
  // 3. Only support Ethereum - ETHUSDT and Bitcoin - BTCUSDT pairs of crypto
  // trading.
  private final AggregatedPriceService aggregatedPriceService;
  private final CryptoUserService cryptoUserService;
  private final TransactionService transactionService;

  private final TradeActionFactory tradeActionFactory;

  @Override
  @Transactional
  public TradeResponse executeTrade(TradeRequest tradeRequest) {
    final var cryptoUser =
        cryptoUserService
            .getByUserId(tradeRequest.getCryptoUserId())
            .orElseThrow(() -> new RuntimeException("Crypto user not found"));
    final var tradePrice =
        aggregatedPriceService
            .requireLatestBestPriceForSymbol(tradeRequest.getSymbol())
            .getPriceForTradeType(tradeRequest.getTradeType());
    final var tradeAction = tradeActionFactory.getTradeAction(tradeRequest.getTradeType());
    tradeAction.execute(cryptoUser, tradeRequest, tradePrice);

    return TradeResponse.builder()
        .symbol(tradeRequest.getSymbol())
        .tradeType(tradeRequest.getTradeType())
        .quantity(tradeRequest.getQuantity())
        .price(tradePrice)
        .build();
  }

  @Override
  public List<Transaction> getTradeHistoryByUserId(Long userId) {
    return transactionService.getAllTransactionsByUserId(userId);
  }
}
