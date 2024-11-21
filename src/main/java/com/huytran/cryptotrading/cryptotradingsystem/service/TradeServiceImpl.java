package com.huytran.cryptotrading.cryptotradingsystem.service;

import com.huytran.cryptotrading.cryptotradingsystem.enums.TradeType;
import com.huytran.cryptotrading.cryptotradingsystem.model.request.TradeRequest;
import com.huytran.cryptotrading.cryptotradingsystem.model.response.TradeResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
    // TODO: Once finish, choose a name between "Trade" or "Transaction"
    // TODO: Consider about Assumption
    //1. User has already authenticated and authorised to access the APIs => This one
    //2. User's initial wallet balance 50,000 USDT in DB record.
    //3. Only support Ethereum - ETHUSDT and Bitcoin - BTCUSDT pairs of crypto
    //trading.
    private final AggregatedPriceService aggregatedPriceService;
    private final CryptoUserService cryptoUserService;
    private final WalletService walletService;
    private final TransactionService transactionService;

    @Override
    @Transactional
    public TradeResponse executeTrade(TradeRequest tradeRequest) {
        final var cryptoUser = cryptoUserService.getByUserId(tradeRequest.getCryptoUserId())
                .orElseThrow(() -> new RuntimeException("Crypto user not found"));
        double tradePrice = getTradePrice(tradeRequest);

        if (TradeType.BUY.equals(tradeRequest.getTradeType())) {
            walletService.buyCrypto(cryptoUser, tradeRequest.getSymbol(), tradeRequest.getTradeType(), tradePrice, tradeRequest.getQuantity());
        } else {
            walletService.sellCrypto(cryptoUser, tradeRequest.getSymbol(), tradeRequest.getTradeType(), tradePrice, tradeRequest.getQuantity());
        }

        return new TradeResponse(tradeRequest.getSymbol(), tradeRequest.getTradeType(), tradeRequest.getQuantity(), tradePrice);
    }

    private double getTradePrice(TradeRequest tradeRequest) {
        final var bestLatestPrice = aggregatedPriceService.requireLatestBestPriceForSymbol(tradeRequest.getSymbol());

        return TradeType.BUY.equals(tradeRequest.getTradeType()) ? bestLatestPrice.getAskPrice() : bestLatestPrice.getBidPrice();
    }
}
