package com.tagsoft.testapi.model.dto;

import lombok.Data;

@Data
public class MonobankDownloadDto {
    private int currencyCodeA;
    private int currencyCodeB;
    private long date;
    private double rateBuy;
    private double rateSell;
    private double rateCross;
}
