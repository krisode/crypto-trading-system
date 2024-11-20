package com.huytran.cryptotrading.cryptotradingsystem.repository;

import com.huytran.cryptotrading.cryptotradingsystem.entity.AggregatedPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AggregatedPriceRepository extends JpaRepository<AggregatedPrice, Long> {
    Optional<AggregatedPrice> findTop1AggregatedPriceBySymbolOrderByTimestampDesc(String symbol);

    @Query(value = """
        SELECT *
        FROM aggregated_price
        WHERE (symbol, timestamp) IN (
            SELECT symbol, MAX(timestamp)
            FROM aggregated_price
            GROUP BY symbol
        )
    """, nativeQuery = true)
    List<AggregatedPrice> findAllGroupedBySymbolOrderByTimestampDesc();
}
