package com.tagsoft.testapi.controller;

import com.tagsoft.testapi.model.dto.SomeCurrencyForPeriodPostDto;
import com.tagsoft.testapi.model.dto.SomeCurrencyGetDto;
import com.tagsoft.testapi.model.dto.generic.ApiResponseWrapper;
import com.tagsoft.testapi.service.SomeCurrencyService;
import com.tagsoft.testapi.service.mapper.SomeCurrencyGetMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/api")
public class CurrencyRateController {

    @Autowired
    SomeCurrencyService someCurrencyService;
    @Autowired
    SomeCurrencyGetMapper someCurrencyGetMapper;

    @ApiOperation(value = "Returns a list of exchange rates for all sources, with average exchange rates")
    @GetMapping("/averages")
    public ResponseEntity<ApiResponseWrapper<List<SomeCurrencyGetDto>>> getAverageRateCurrencies() {
        return ResponseEntity.ok(new ApiResponseWrapper(someCurrencyGetMapper.someCurrencyGetDtoList(someCurrencyService.getAllWithAverages())));
    }

    @ApiOperation(value = "Returns a list of average exchange rates for all sources for the period")
    @PostMapping("/averages/period")
    public ResponseEntity<ApiResponseWrapper<List<SomeCurrencyGetDto>>> getAverageRateCurrenciesForPeriod(@RequestBody SomeCurrencyForPeriodPostDto dto) {
        return ResponseEntity.ok(new ApiResponseWrapper(someCurrencyGetMapper.someCurrencyGetDtoList(someCurrencyService.getAllWithAveragesForPeriod(dto.getStartPeriod(), dto.getEndPeriod()))));
    }


}
