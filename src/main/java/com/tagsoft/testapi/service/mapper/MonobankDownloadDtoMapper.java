package com.tagsoft.testapi.service.mapper;

import com.tagsoft.testapi.model.dto.MonobankDownloadDto;
import com.tagsoft.testapi.model.entity.CurrencyExchangeEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MonobankDownloadDtoMapper {
    private static Map<Integer, Currency> currencies = new HashMap<>();

    public MonobankDownloadDtoMapper() {
        Set<Currency> set = Currency.getAvailableCurrencies();
        for (Currency currency : set) {
            currencies.put(currency.getNumericCode(), currency);
        }
    }

    public CurrencyExchangeEntity getFromMobobankDTO(MonobankDownloadDto monobankDownloadDTO) {
        return CurrencyExchangeEntity.builder()
                .baseCurrency(getCurrencyCode(monobankDownloadDTO.getCurrencyCodeB()))
                .exchangeCurrency(getCurrencyCode(monobankDownloadDTO.getCurrencyCodeA()))
                .buyRate(monobankDownloadDTO.getRateSell())
                .sellRate(monobankDownloadDTO.getRateBuy())
                .crossRate(monobankDownloadDTO.getRateCross())
                .loadingDate(Timestamp.valueOf(LocalDateTime.ofEpochSecond(monobankDownloadDTO.getDate(), 0, ZoneOffset.UTC)))
                .bankName("Monobank")
                .build();
    }

    public List<CurrencyExchangeEntity> getFromMonobankDTOList(List<MonobankDownloadDto> monobankDownloadDtos) {
        return monobankDownloadDtos.stream().map(this::getFromMobobankDTO).collect(Collectors.toList());
    }

    private String getCurrencyCode(int numericValue) {
        return currencies.get(numericValue).getCurrencyCode();
    }
}
