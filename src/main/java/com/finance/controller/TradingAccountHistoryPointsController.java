package com.finance.controller;

import com.finance.domain.dto.TradingAccountHistoryPointDto;
import com.finance.mapper.TradingAccountHistoryPointMapper;
import com.finance.service.database.TradingAccountHistoryPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/finance")
public class TradingAccountHistoryPointsController {

    @Autowired
    private TradingAccountHistoryPointMapper tradingAccountHistoryPointMapper;

    @Autowired
    private TradingAccountHistoryPointService tradingAccountHistoryPointService;

    @GetMapping(value = "/accountHistoryPoints/{userId}")
    public List<TradingAccountHistoryPointDto> getAccountHistoryPoints(@PathVariable Long userId){
        return tradingAccountHistoryPointMapper.mapToAccountHistoryPointDtos(
                tradingAccountHistoryPointService.getTradingAccountHistoryPoints(userId));
    }
}
