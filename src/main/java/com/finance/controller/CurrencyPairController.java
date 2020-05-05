package com.finance.controller;
import com.finance.domain.CurrencyPair;
import com.finance.mapper.CurrencyPairMapper;
import com.finance.service.database.CurrencyPairService;
import com.finance.service.database.communicationObjects.DatabaseEntity;
import com.finance.service.database.communicationObjects.DatabaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/finance")
public class CurrencyPairController {
    @Autowired
    private CurrencyPairService currencyPairService;

    @Autowired
    private CurrencyPairMapper currencyPairMapper;

    @GetMapping(value = "/currency/pairs")
    public List<String> getAvailablePairs(){
        DatabaseResponse databaseResponse = currencyPairService.getCurrencies();

        List<DatabaseEntity> entities = null;
        if(databaseResponse.isOK()){
            entities = databaseResponse.getRequestedObjects();
        }
        List<CurrencyPair> currencyPairs = new ArrayList<>();
        for(DatabaseEntity entity : entities){
            if(entity instanceof CurrencyPair)
            currencyPairs.add((CurrencyPair) entity);
        }

        return currencyPairMapper.mapToPairsStringList(currencyPairs);
    }

    //@GetMapping(value = "/currency/pair")
    //public CurrencyPairDataDto getCurrency(@RequestBody PairDataRequestDto pairDataRequestDto){
      //  return currencyPairMapper.mapToPairDataDto(currencyPairService.getCurrencyPair(pairDataRequestDto.getCurrencyName()));
    //}
}
