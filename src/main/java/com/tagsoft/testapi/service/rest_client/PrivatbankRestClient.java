package com.tagsoft.testapi.service.rest_client;

import com.tagsoft.testapi.model.dto.PrivatbankDownloadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

@Component
public class PrivatbankRestClient {
    public static final String PRIVATBANK_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    private static final HttpMethod GET_METHOD = HttpMethod.GET;

    @Autowired
    private HttpRestClient httpRestClient;

    public List<PrivatbankDownloadDto> getCurrentCurrencyExchangeRates() {
        PrivatbankDownloadDto[] privatbankDownloadDtos = httpRestClient.executeRequest(
                createHeaders(), PRIVATBANK_URL, GET_METHOD, null, PrivatbankDownloadDto[].class);
        return Arrays.asList(privatbankDownloadDtos);
    }

    private MultiValueMap<String, String> createHeaders() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        return headers;
    }
}
