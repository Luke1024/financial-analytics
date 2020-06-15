package com.finance.controller;
import com.finance.domain.CurrencyPair;
import com.finance.mapper.CurrencyPairMapper;
import com.finance.service.database.CurrencyPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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

        List<CurrencyPair> currencyPairs = currencyPairService.getCurrencies();

        if(currencyPairs != null) {
            return currencyPairMapper.mapToPairsStringList(currencyPairs);
        }
        return Collections.emptyList();
    }
}
