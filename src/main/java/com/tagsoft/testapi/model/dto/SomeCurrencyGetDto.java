package com.tagsoft.testapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SomeCurrencyGetDto {
    private String baseCurrency;
    private String exchangeCurrency;
    private double buyRate;
    private double sellRate;
    private double crossRate;
}
