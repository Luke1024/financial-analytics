package com.finance.controller;

import com.finance.domain.dto.TradingAccountLeverageModDto;
import com.finance.domain.dto.TradingAccountCreationDto;
import com.finance.domain.dto.TradingAccountDto;
import com.finance.mapper.TradingAccountMapper;
import com.finance.service.TradingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/finance")
public class TradingAccountController {
    @Autowired
    private TradingAccountService tradingAccountService;

    @Autowired
    private TradingAccountMapper tradingAccountMapper;

    @GetMapping(value = "/tradingAccount/{userId}")
    public List<TradingAccountDto> getUserTradingAccounts(@PathVariable Long userId){
        return tradingAccountMapper.mapToTradingDtoList(tradingAccountService.getUserTradingAccounts(userId));
    }

    @PostMapping(value = "/tradingAccount", consumes = APPLICATION_JSON_VALUE)
    public void createUserTradingAccount(@RequestBody TradingAccountCreationDto tradingAccountCreationDto) {
        tradingAccountService.createTradingAccount(tradingAccountMapper.mapToNewTradingAccount(tradingAccountCreationDto));
    }

    @PutMapping(value = "/tradingAccount", consumes = APPLICATION_JSON_VALUE)
    public TradingAccountDto changeLeverageInTradingAccount(@RequestBody TradingAccountLeverageModDto tradingAccountLeverageModDto) {
        return tradingAccountMapper.mapToTradingAccountDto(
                tradingAccountService.modifyLeverageInAccount(tradingAccountLeverageModDto));
    }

    @DeleteMapping(value = "/tradingAccount", consumes = APPLICATION_JSON_VALUE)
    public void archiveUserTradingAccount(@RequestParam Long accountId) {
        tradingAccountService.archiveAccount(accountId);
    }
}
