-- Preload Supported Trading Pairs
INSERT INTO trading_pair (pair) VALUES ('BTCUSDT'), ('ETHUSDT');

-- Preload Users with Wallet Balance
INSERT INTO crypto_user (username, wallet_balance) VALUES ('test_user', 50000.00);
