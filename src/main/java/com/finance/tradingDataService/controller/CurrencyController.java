package com.finance.tradingDataService.controller;

import com.finance.tradingDataService.domain.dto.CurrencyHistoryPointDto;
import com.finance.tradingDataService.domain.dto.CurrencyHistoryRequestDto;
import com.finance.tradingDataService.mapper.CurrencyMapper;
import com.finance.tradingDataService.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/finance")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyMapper currencyMapper;

    @GetMapping(value = "/currency/")
    public List<String> getAllAvailableCurrencies(){
        return currencyMapper.mapToStringKeys(currencyService.getCurrencies());
    }

    @GetMapping(value = "/currency/history")
    public List<CurrencyHistoryPointDto> getCurrencyHistory(@RequestBody CurrencyHistoryRequestDto currencyHistoryRequestDto){
        return currencyMapper.mapToHistoryPointDto(currencyService.getCurrencyHistory(currencyHistoryRequestDto));
    }
}
