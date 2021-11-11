package com.tagsoft.testapi.service.mapper;

import com.tagsoft.testapi.model.dto.PrivatbankDownloadDto;
import com.tagsoft.testapi.model.entity.CurrencyExchangeEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrivatbankDownloadDtoMapper {
    public CurrencyExchangeEntity getFromPrivatebankDTO(PrivatbankDownloadDto privatbankDownloadDto) {
        return CurrencyExchangeEntity.builder()
                .baseCurrency(privatbankDownloadDto.getBase_ccy())
                .exchangeCurrency(privatbankDownloadDto.getCcy())
                .sellRate(privatbankDownloadDto.getSale())
                .buyRate(privatbankDownloadDto.getBuy())
                .loadingDate(Timestamp.valueOf(LocalDateTime.now()))
                .bankName("Privatbank")
                .build();
    }

    public List<CurrencyExchangeEntity> getFromPrivatebankDTOList(List<PrivatbankDownloadDto> privatbankDownloadDtos) {
        return privatbankDownloadDtos.stream().map(this::getFromPrivatebankDTO).collect(Collectors.toList());
    }
}
