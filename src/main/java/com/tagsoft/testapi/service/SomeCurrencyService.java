package com.tagsoft.testapi.service;

import com.tagsoft.testapi.model.entity.CurrencyExchangeEntity;

import java.sql.Timestamp;
import java.util.List;

public interface SomeCurrencyService {
    void saveDownloadCurrencyRateList (List<CurrencyExchangeEntity> currencyList);

    List<CurrencyExchangeEntity> getAllWithAverages();

    List<CurrencyExchangeEntity> getAllWithAveragesForPeriod(Timestamp startPeriod, Timestamp endPeriod);
}
