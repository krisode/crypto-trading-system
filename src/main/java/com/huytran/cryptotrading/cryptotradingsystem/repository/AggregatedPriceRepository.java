package com.huytran.cryptotrading.cryptotradingsystem.repository;

import com.huytran.cryptotrading.cryptotradingsystem.entity.AggregatedPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AggregatedPriceRepository extends JpaRepository<AggregatedPrice, Long> {
}
