package com.huytran.cryptotrading.cryptotradingsystem.mapper;

import com.huytran.cryptotrading.cryptotradingsystem.entity.Transaction;
import com.huytran.cryptotrading.cryptotradingsystem.model.response.TradeHistoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TradeMapper {

    TradeHistoryResponse toResponse(Transaction transaction);

    List<TradeHistoryResponse> toResponse(List<Transaction> transaction);
}
