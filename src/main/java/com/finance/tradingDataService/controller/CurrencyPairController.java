package com.finance.tradingDataService.controller;

import com.finance.tradingDataService.domain.dto.PairHistoryPointDto;
import com.finance.tradingDataService.domain.dto.PairHistoryRequestDto;
import com.finance.tradingDataService.mapper.CurrencyPairMapper;
import com.finance.tradingDataService.service.currency.CurrencyPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/finance")
public class CurrencyPairController {
    @Autowired
    private CurrencyPairService currencyPairService;

    @Autowired
    private CurrencyPairMapper currencyPairMapper;

    @GetMapping(value = "/currency/pair/history")
    public List<PairHistoryPointDto> currencyPairHistory(PairHistoryRequestDto pairHistoryRequestDto){
        return currencyPairMapper.mapToDTOList(currencyPairService.getCurrencyPairHistory(pairHistoryRequestDto));
    }
}
