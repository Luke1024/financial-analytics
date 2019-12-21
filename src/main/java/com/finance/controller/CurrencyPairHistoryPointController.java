package com.finance.controller;

import com.finance.service.CurrencyPairHistoryPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/finance")
public class CurrencyPairHistoryPointController {
    @Autowired
    private CurrencyPairHistoryPointService currencyPairHistoryPointService;

    //@Autowired
    //private CurrencyPairHistoryPointMapper currencyPairHistoryPointMapper;

    //@GetMapping(value = "/currency/pairs")
    //private List<CurrencyPairHistoryPointDto> getCurrencyPairHistory(PairHistoryRequestDto pairHistoryRequestDto) {
      //  return currencyPairHistoryPointMapper.mapToCurrencyPairHistoryPointDto(
        //currencyPairHistoryPointService.getCurrencyPairHistory(pairHistoryRequestDto));
    //}
}
