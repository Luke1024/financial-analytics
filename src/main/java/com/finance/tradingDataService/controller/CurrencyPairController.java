package com.finance.tradingDataService.controller;

import com.finance.tradingDataService.domain.dto.CurrencyPairStatusDTO;
import com.finance.tradingDataService.mapper.CurrencyPairMapper;
import com.finance.tradingDataService.service.CurrencyPairService;
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

    @GetMapping(value = "/currency/pair/status")
    public List<CurrencyPairStatusDTO> currencyPairStatusList(){
        return currencyPairMapper.mapToDTOList(currencyPairMapper.getAvailablePairsStatus());
    }
}
