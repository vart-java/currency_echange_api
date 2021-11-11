package com.tagsoft.testapi.service.impl;

import com.tagsoft.testapi.repository.CurrencyRepository;
import com.tagsoft.testapi.exception.IllegalTimestampException;
import com.tagsoft.testapi.model.entity.CurrencyExchangeEntity;
import com.tagsoft.testapi.service.SomeCurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SomeCurrencyServiceImpl implements SomeCurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public void saveDownloadCurrencyRateList(List<CurrencyExchangeEntity> currencyList) {
        currencyRepository.saveAll(currencyList);
    }

    @Override
    public List<CurrencyExchangeEntity> getAllWithAverages() {
        return currencyRepository.findTest(Timestamp.valueOf(LocalDateTime.now().minusDays(1)),
                        Timestamp.valueOf(LocalDateTime.now().plusDays(1)))
                .stream()
                .collect(Collectors.groupingBy(CurrencyExchangeEntity::getExchangeCurrency))
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .map(this::getAverageFromList)
                .collect(Collectors.toList());
    }

    @Override
    public List<CurrencyExchangeEntity> getAllWithAveragesForPeriod(Timestamp startPeriod, Timestamp endPeriod) {
        if (startPeriod.getTime() < 0 ||
                endPeriod.getTime() < 0 ||
                startPeriod.getTime() > Timestamp.valueOf(LocalDateTime.now()).getTime() ||
                endPeriod.getTime() > Timestamp.valueOf(LocalDateTime.now()).getTime()
        ) {
            log.error("Exception during data parse");
            throw new IllegalTimestampException("Timestamp is not real");
        }
        return currencyRepository.findTest(startPeriod, endPeriod)
                .stream()
                .collect(Collectors.groupingBy(CurrencyExchangeEntity::getExchangeCurrency))
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .map(this::getAverageFromList)
                .collect(Collectors.toList());
    }

    private CurrencyExchangeEntity getAverageFromList(List<CurrencyExchangeEntity> list) {
        CurrencyExchangeEntity currencyExchangeEntity = list.get(0);
        double sumSellRate = 0;
        double sumBuyRate = 0;
        double sumCrossRate = 0;
        for (CurrencyExchangeEntity s : list) {
            if (s.getSellRate() != 0) {
                sumSellRate += s.getSellRate();
            }
            if (s.getBuyRate() != 0) {
                sumBuyRate += s.getBuyRate();
            }
            if (s.getCrossRate() != 0) {
                sumCrossRate += s.getCrossRate();
            }
        }
        currencyExchangeEntity.setBuyRate(sumBuyRate / list.size());
        currencyExchangeEntity.setSellRate(sumSellRate / list.size());
        currencyExchangeEntity.setCrossRate(sumCrossRate / list.size());
        currencyExchangeEntity.setBankName("Average");
        currencyExchangeEntity.setLoadingDate(Timestamp.valueOf(LocalDateTime.now()));
        return currencyExchangeEntity;
    }
}
