package com.finance.data.controller.currency;

import com.finance.data.domain.currency.dto.CurrencyOverviewDto;
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

    @GetMapping(value = "/currency/pairs")
    public List<CurrencyOverviewDto> getCurrencies(){
        return currencyPairMapper.mapToOverviewDto(currencyPairService.getCurrencies());
    }
}
