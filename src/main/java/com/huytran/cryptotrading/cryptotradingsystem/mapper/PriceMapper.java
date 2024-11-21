package com.huytran.cryptotrading.cryptotradingsystem.mapper;

import com.huytran.cryptotrading.cryptotradingsystem.entity.AggregatedPrice;
import com.huytran.cryptotrading.cryptotradingsystem.model.response.LatestBestPrice;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceMapper {

    LatestBestPrice toResponse(AggregatedPrice aggregatedPrice);

    List<LatestBestPrice> toResponse(List<AggregatedPrice> aggregatedPrice);
}
