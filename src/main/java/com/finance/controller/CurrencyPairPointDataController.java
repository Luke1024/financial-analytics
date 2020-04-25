package com.finance.controller;

import com.finance.domain.dto.currencyPair.PairDataRequestDto;
import com.finance.mapper.CurrencyPairDataPointMapper;
import com.finance.preprocessor.utilities.DataPoint;
import com.finance.service.database.CurrencyPairDataPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/finance")
public class CurrencyPairPointDataController {

    @Autowired
    private CurrencyPairDataPointService service;

    @Autowired
    private CurrencyPairDataPointMapper mapper;

    @GetMapping(value = "/currency/pairs/data")
    public List<DataPoint> getCurrencyPairDataPoints(@RequestBody PairDataRequestDto pairDataRequestDto){
        //return mapper.mapToDataPoints(service.getCurrencyPairHistory(pairDataRequestDto));
        return new ArrayList<>();
    }
}
