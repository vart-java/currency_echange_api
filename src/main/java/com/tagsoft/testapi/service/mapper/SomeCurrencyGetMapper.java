package com.tagsoft.testapi.service.mapper;

import com.tagsoft.testapi.model.dto.SomeCurrencyGetDto;
import com.tagsoft.testapi.model.entity.CurrencyExchangeEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SomeCurrencyGetMapper {
    public SomeCurrencyGetDto someCurrencyGetDto(CurrencyExchangeEntity currencyExchangeEntity) {
        return SomeCurrencyGetDto.builder()
                .baseCurrency(currencyExchangeEntity.getBaseCurrency())
                .exchangeCurrency(currencyExchangeEntity.getExchangeCurrency())
                .buyRate(currencyExchangeEntity.getBuyRate())
                .sellRate(currencyExchangeEntity.getSellRate())
                .crossRate(currencyExchangeEntity.getCrossRate())
                .build();
    }

    public List<SomeCurrencyGetDto> someCurrencyGetDtoList(List<CurrencyExchangeEntity> someCurrencies) {
        return someCurrencies.stream().map(this::someCurrencyGetDto).collect(Collectors.toList());
    }
}
