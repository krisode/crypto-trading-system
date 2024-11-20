-- Users Table
CREATE TABLE crypto_user
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    username       VARCHAR(255)   NOT NULL UNIQUE
);

-- Wallet table
CREATE TABLE wallet
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    user_id  INT            NOT NULL,
    currency VARCHAR(10)    NOT NULL, -- ETH, BTC, USDT
    balance  DECIMAL(18, 8) NOT NULL DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES crypto_user (id),
    UNIQUE (user_id, currency)
);


-- Aggregated Prices Table
CREATE TABLE aggregated_price
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    symbol    VARCHAR(10)    NOT NULL, -- ETHUSDT, BTCUSDT
    bid_price DECIMAL(18, 8) NOT NULL, -- Used for SELL orders
    ask_price DECIMAL(18, 8) NOT NULL, -- Used for BUY orders
    timestamp DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Transactions Table
CREATE TABLE transaction
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    user_id   INT            NOT NULL,
    symbol    VARCHAR(10)    NOT NULL, -- ETHUSDT, BTCUSDT
    type      ENUM('BUY', 'SELL') NOT NULL,
    price     DECIMAL(18, 8) NOT NULL,
    quantity  DECIMAL(18, 8) NOT NULL,
    timestamp DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES crypto_user (id)
);
