package com.huytran.cryptotrading.cryptotradingsystem.mapper;

import com.huytran.cryptotrading.cryptotradingsystem.entity.AggregatedPrice;
import com.huytran.cryptotrading.cryptotradingsystem.model.response.LatestBestPriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceMapper {

    LatestBestPriceResponse toResponse(AggregatedPrice aggregatedPrice);

    List<LatestBestPriceResponse> toResponse(List<AggregatedPrice> aggregatedPrice);
}
