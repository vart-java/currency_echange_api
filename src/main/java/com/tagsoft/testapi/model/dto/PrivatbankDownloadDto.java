package com.tagsoft.testapi.model.dto;

import lombok.Data;

@Data
public class PrivatbankDownloadDto {
    private String ccy;
    private String base_ccy;
    private double buy;
    private double sale;
}
