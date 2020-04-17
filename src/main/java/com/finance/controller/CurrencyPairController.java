package com.finance.controller;

import com.finance.domain.dto.currencyPair.CurrencyPairDataDto;
import com.finance.domain.dto.currencyPair.PairRequestDto;
import com.finance.mapper.CurrencyPairMapper;
import com.finance.service.CurrencyPairService;
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

    @GetMapping(value = "/currency/pairs")
    public List<String> getAvailablePairs(){
        return currencyPairMapper.mapToPairsStringList(currencyPairService.getCurrencies());
    }

    @GetMapping(value = "/currency/pair")
    public CurrencyPairDataDto getCurrency(@RequestBody PairRequestDto pairRequestDto){
        return currencyPairMapper.mapToOverviewDto(currencyPairService.getCurrencyPair(pairRequestDto.getPairName()));
    }
}
