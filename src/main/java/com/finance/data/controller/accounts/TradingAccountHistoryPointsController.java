package com.finance.data.controller.accounts;

import com.finance.data.domain.accounts.dto.TradingAccountHistoryPointDto;
import com.finance.data.mapper.accounts.TradingAccountHistoryPointMapper;
import com.finance.data.service.account.TradingAccountHistoryPointService;
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
