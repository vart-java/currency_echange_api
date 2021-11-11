package com.tagsoft.testapi.model.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class SomeCurrencyForPeriodPostDto {
    private Timestamp startPeriod;
    private Timestamp endPeriod;
}
