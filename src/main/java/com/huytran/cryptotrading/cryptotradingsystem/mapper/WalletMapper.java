package com.huytran.cryptotrading.cryptotradingsystem.mapper;

import com.huytran.cryptotrading.cryptotradingsystem.entity.Wallet;
import com.huytran.cryptotrading.cryptotradingsystem.model.response.WalletBalanceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WalletMapper {

    WalletBalanceResponse toResponse(Wallet wallet);

    List<WalletBalanceResponse> toResponse(List<Wallet> wallets);
}
