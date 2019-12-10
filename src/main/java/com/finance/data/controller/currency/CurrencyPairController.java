package com.finance.data.controller.currency;

import com.finance.data.domain.currency.dto.PairHistoryPointDto;
import com.finance.data.domain.currency.dto.PairHistoryRequestDto;
import com.finance.data.mapper.currency.CurrencyPairMapper;
import com.finance.data.service.currency.CurrencyPairService;
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

    @GetMapping(value = "/currency/primarypair")
    public List<PairHistoryPointDto> getCurrencies(){
        return
    }
}
