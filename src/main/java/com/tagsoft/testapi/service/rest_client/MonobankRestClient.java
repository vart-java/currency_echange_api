package com.tagsoft.testapi.service.rest_client;

import com.tagsoft.testapi.model.dto.MonobankDownloadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

@Component
public class MonobankRestClient {
    private static final String MONOBANK_URL = "https://api.monobank.ua/bank/currency";
    private static final HttpMethod GET_METHOD = HttpMethod.GET;

    @Autowired
    private HttpRestClient httpRestClient;

    public List<MonobankDownloadDto> getCurrentCurrencyExchangeRates() {
        MonobankDownloadDto[] monobankDownloadDtos = httpRestClient.executeRequest(
                createHeaders(), MONOBANK_URL, GET_METHOD, null, MonobankDownloadDto[].class);
        return Arrays.asList(monobankDownloadDtos);
    }

    private MultiValueMap<String, String> createHeaders() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        return headers;
    }
}
