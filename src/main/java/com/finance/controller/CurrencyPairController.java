package com.finance.controller;
import com.finance.mapper.CurrencyPairMapper;
import com.finance.service.database.CurrencyPairService;
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

    //@GetMapping(value = "/currency/pair")
    //public CurrencyPairDataDto getCurrency(@RequestBody PairDataRequestDto pairDataRequestDto){
      //  return currencyPairMapper.mapToPairDataDto(currencyPairService.getCurrencyPair(pairDataRequestDto.getCurrencyName()));
    //}
}
