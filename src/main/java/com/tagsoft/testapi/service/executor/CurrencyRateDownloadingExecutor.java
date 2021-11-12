package com.tagsoft.testapi.service.executor;

import com.tagsoft.testapi.service.SomeCurrencyService;
import com.tagsoft.testapi.service.rest_client.MonobankRestClient;
import com.tagsoft.testapi.service.rest_client.PrivatbankRestClient;
import com.tagsoft.testapi.service.mapper.MonobankDownloadDtoMapper;
import com.tagsoft.testapi.service.mapper.PrivatbankDownloadDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
public class CurrencyRateDownloadingExecutor {
    @Autowired
    SomeCurrencyService currencyService;
    @Autowired
    PrivatbankDownloadDtoMapper privatbankDownloadDtoMapper;
    @Autowired
    MonobankDownloadDtoMapper monobankDownloadDtoMapper;
    @Autowired
    MonobankRestClient monobankRestClient;
    @Autowired
    PrivatbankRestClient privatbankRestClient;


    @Scheduled(fixedDelay = 86400000L)
    public void executePrivatbankDownloadJob() {
        currencyService.saveDownloadCurrencyRateList(privatbankDownloadDtoMapper
                .getFromPrivatebankDTOList(privatbankRestClient.getCurrentCurrencyExchangeRates()));
    }

    @Scheduled(fixedDelay = 86400000L)
    public void executeMonobankDownloadJob() {
        currencyService.saveDownloadCurrencyRateList(monobankDownloadDtoMapper
                .getFromMonobankDTOList(monobankRestClient.getCurrentCurrencyExchangeRates()));
    }
}
