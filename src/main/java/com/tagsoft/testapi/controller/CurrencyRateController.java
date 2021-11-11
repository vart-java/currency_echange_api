package com.tagsoft.testapi.controller;

import com.tagsoft.testapi.model.dto.SomeCurrencyForPeriodPostDto;
import com.tagsoft.testapi.model.dto.SomeCurrencyGetDto;
import com.tagsoft.testapi.model.dto.generic.ApiResponseWrapper;
import com.tagsoft.testapi.service.SomeCurrencyService;
import com.tagsoft.testapi.service.mapper.SomeCurrencyGetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CurrencyRateController {

    @Autowired
    SomeCurrencyService someCurrencyService;
    @Autowired
    SomeCurrencyGetMapper someCurrencyGetMapper;

    @GetMapping("/averages")
    public ResponseEntity<ApiResponseWrapper<List<SomeCurrencyGetDto>>> getAverageRateCurrencies() {
        return ResponseEntity.ok(new ApiResponseWrapper(someCurrencyGetMapper.someCurrencyGetDtoList(someCurrencyService.getAllWithAverages())));
    }

    @PostMapping("/averages/period")
    public ResponseEntity<ApiResponseWrapper<List<SomeCurrencyGetDto>>> getAverageRateCurrenciesForPeriod(@RequestBody SomeCurrencyForPeriodPostDto dto) {
        return ResponseEntity.ok(new ApiResponseWrapper(someCurrencyGetMapper.someCurrencyGetDtoList(someCurrencyService.getAllWithAverages())));
    }


}
