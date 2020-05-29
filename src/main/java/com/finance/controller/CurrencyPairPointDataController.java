package com.finance.controller;

import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.currencyPair.DataPointDto;
import com.finance.domain.dto.PairDataRequest;
import com.finance.domain.dto.currencyPair.DataPointDtoPack;
import com.finance.domain.dto.currencyPair.PairDataRequestDto;
import com.finance.mapper.CurrencyPairDataPointMapper;
import com.finance.preprocessor.utilities.DataBaseLoader;
import com.finance.service.database.CurrencyPairDataPointService;
import com.finance.service.database.communicationObjects.DatabaseResponse;
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

    private Logger logger = Logger.getLogger(CurrencyPairDataPointService.class.getName());

    @Autowired
    private CurrencyPairDataPointService service;

    @Autowired
    private CurrencyPairDataPointMapper mapper;

    @PostMapping(value = "/currency/pairs/data")
    public DataPointDtoPack getCurrencyPairDataPoints(@RequestBody PairDataRequestDto pairDataRequestdto){
        PairDataRequest pairDataRequest = mapper.mapToPairDataRequest(pairDataRequestdto);
        DatabaseResponse databaseResponse;
        if(pairDataRequest == null) {
            return new DataPointDtoPack(new ArrayList<>());
        } else {
            databaseResponse = service.getCurrencyPairHistory(pairDataRequest);
        }
        try {
            if (databaseResponse.isOK()) {
                List<CurrencyPairDataPoint> dataPoints = databaseResponse.getRequestedObjects()
                        .stream().map(databaseEntity -> (CurrencyPairDataPoint) databaseEntity).collect(Collectors.toList());
                return new DataPointDtoPack(mapper.mapToDataPoints(dataPoints));
            }
        } catch (Exception e){
            logger.log(Level.WARNING, e.toString());
        }
        return new DataPointDtoPack(new ArrayList<>());
    }
}
