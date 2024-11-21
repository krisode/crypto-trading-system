# **Crypto Trading System**

Welcome to the **Crypto Trading System**, a robust and scalable application designed for aggregating cryptocurrency prices, providing trading data, and integrating with popular exchanges such as Binance and Huobi. This system is built with industry best practices, ensuring high maintainability, performance, and flexibility.

---

## **Table of Contents**
1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Project Structure](#project-structure)
4. [Setup and Installation](#setup-and-installation)
5. [API Endpoints](#api-endpoints)
6. [Contact](#contact)
---

## **Features**
- 🔄 **Real-Time Price Aggregation**: Fetch and consolidate cryptocurrency prices from multiple exchanges.
- 📊 **Historical Trade Data**: Retrieve detailed trade history and insights.
- 🛠️ **Modular Architecture**: Supports seamless addition of new exchanges or services.
- 📅 **Scheduled Tasks**: Automates periodic data fetching with robust scheduling capabilities.
- ⚙️ **Configurable**: Easy-to-manage settings for API integration, scheduling, and trading rules.

---

## **Technologies Used**
- **Java 17**: Modern Java for performance and feature-rich programming.
- **Spring Boot**: Microservices framework for dependency injection, RESTful APIs, and configuration management.
- **JPA (Hibernate)**: Object-relational mapping for database management.
- **Feign Clients**: Simplified HTTP client for interacting with external APIs.
- **MapStruct**: DTO-to-entity mapping to reduce boilerplate code.
- **Lombok**: Simplified getter, setter, and constructor generation.
- **H2 Database**: A fast in-memory database.
- **Scheduler**: Spring Scheduler for periodic tasks.

---

## **Project Structure**
```plaintext
com.huytran.cryptotrading.cryptotradingsystem
│
├── config              # Configuration classes
|
├── controller          # REST controllers
|
├── entity              # JPA entity classes
|
├── enums               # Enum definitions
|
├── repository          # Repository interfaces
|
├── mapper              # MapStruct for DTO mapping
|
├── model
│   └── request         # Controller request  
│   └── response        # Controller response       
│
├── service             # Service interface & implementation
│   ├── action          # Action for execution
│   ├── factory         # Factory for services
│   ├── provider        # Provider for services
│
├── connector           # Service
│   ├── feignclient     # Feign client interface
│   └── request         # Request sent in API
│   └── response        # Response mapping from API result
│
└── scheduler           # Task scheduling logic      
```

---

## **Setup and Installation**

### **Prerequisites**
- **Java 17** installed.
- **Maven** for dependency management.

---

### **Steps**
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/crypto-trading-system.git
   cd crypto-trading-system
   ```
2. Configure the application:
   - Update the database credentials in `application.properties`
     ```bash
       spring.datasource.username=sa
       spring.datasource.password=password
     ```
   - Update H2 database console endpoint:
     ```bash
       spring.h2.console.path=/h2-console
     ```
3. Build and run the project:
      ```bash
      mvn clean install 
      java -jar target/crypto-trading-system-0.0.1-SNAPSHOT.jar
      ```
4. Access the database:
   - http://localhost:8080/h2-console

5. Send API request:
   - Host: http://localhost:8080
   - Endpoint: refer to [API Endpoints](#api-endpoints)

----

### **API Endpoints**
| HTTP Method |                    Endpoint                     |                    Description                    |
|:-----------:|:-----------------------------------------------:|:-------------------------------------------------:|
|     GET     |              `/api/prices/latest`               |           Get latest aggregated prices            |
|     GET     |          `/api/prices/latest/{symbol}`          | Get latest aggregated prices by a specific symbol |
|    POST     |                  `/api/trades`                  |            Submit a new trade request             |
|     GET     |              `/api/trades/history`              |             Get historical trade data             |
|     GET     |             `/api/wallets/balance`              |              Get all wallet balances              |
|    POST     |        `/api/wallets/balance/{currency}`        |     Get wallet balance by a specific currency     |

---

### **Contact**
- For issues or inquiries, feel free to contact: **Huy Tran**
- Email: trantrindanghuy1406@gmail.com
- LinkedIn: https://www.linkedin.com/in/tran-trinh-dang-huy-11b9291b3/