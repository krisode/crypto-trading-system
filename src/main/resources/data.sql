-- Preload Users with Wallet Balance
INSERT INTO crypto_user (username) VALUES ('init_user');
INSERT INTO wallet (user_id, currency, balance) VALUES (1, 'USDT', 50000.0)
