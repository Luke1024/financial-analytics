package com.finance.data.controller.currency;

import com.finance.data.domain.currency.dto.CurrencyPairHistoryPointDto;
import com.finance.data.domain.currency.dto.PairHistoryRequestDto;
import com.finance.data.service.currency.CurrencyPairHistoryPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
