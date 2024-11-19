-- Users Table
CREATE TABLE crypto_user
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    username       VARCHAR(255)   NOT NULL UNIQUE,
    wallet_balance DECIMAL(15, 2) NOT NULL
);

-- Trading Pairs Table
CREATE TABLE trading_pair
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    pair VARCHAR(10) NOT NULL UNIQUE
);

-- Aggregated Prices Table
CREATE TABLE aggregated_price
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    pair      VARCHAR(10)    NOT NULL,
    bid_price DECIMAL(15, 2) NOT NULL, -- Used for SELL orders
    ask_price DECIMAL(15, 2) NOT NULL, -- Used for BUY orders
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Transactions Table
CREATE TABLE transaction
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id             BIGINT         NOT NULL,
    pair                VARCHAR(10)    NOT NULL,
    transaction_type    VARCHAR(10)    NOT NULL, -- 'BUY' or 'SELL'
    price               DECIMAL(15, 2) NOT NULL,
    quantity            DECIMAL(15, 8) NOT NULL,
    timestamp           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES crypto_user (id)
);
