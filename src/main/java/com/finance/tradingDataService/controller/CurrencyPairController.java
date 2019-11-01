package com.finance.tradingDataService.controller;

import com.finance.tradingDataService.mapper.CurrencyPairMapper;
import com.finance.tradingDataService.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/finance")
public class CurrencyPairController {
    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyPairMapper currencyPairMapper;

    @GetMapping(value = "/currency/pair/history")
    public List<CurrencyPairHistoryDto> currencyPairHistory(PairHistoryRequest pairHistoryRequest){
        return currencyPairMapper.mapToDTOList(currencyPairMapper.getAvailablePairsStatus());
    }
}
