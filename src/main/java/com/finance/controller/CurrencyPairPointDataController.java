package com.finance.controller;

import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.currencyPair.DataPointDto;
import com.finance.domain.dto.PairDataRequest;
import com.finance.domain.dto.currencyPair.DataPointDtoPack;
import com.finance.domain.dto.currencyPair.PairDataRequestDto;
import com.finance.mapper.CurrencyPairDataPointMapper;
import com.finance.preprocessor.utilities.DataBaseLoader;
import com.finance.service.database.CurrencyPairDataPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/finance")
public class CurrencyPairPointDataController {

    private Logger logger = Logger.getLogger(CurrencyPairPointDataController.class.getName());

    @Autowired
    private CurrencyPairDataPointService service;

    @Autowired
    private CurrencyPairDataPointMapper mapper;

    @PostMapping(value = "/currency/pairs/data")
    public DataPointDtoPack getCurrencyPairDataPoints(@RequestBody PairDataRequestDto pairDataRequestdto){
        PairDataRequest pairDataRequest = mapper.mapToPairDataRequest(pairDataRequestdto);
        if(pairDataRequest == null) {
            return null;
        }
        List<CurrencyPairDataPoint> currencyPairDataPoints = service.getCurrencyPairHistory(pairDataRequest);

        List<DataPointDto> dataPointDtos = mapper.mapToDataPoints(currencyPairDataPoints);

        return new DataPointDtoPack(dataPointDtos);
    }
}
