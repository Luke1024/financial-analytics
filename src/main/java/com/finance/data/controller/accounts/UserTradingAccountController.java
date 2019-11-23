package com.finance.data.controller.accounts;

import com.finance.data.domain.accounts.dto.TradingAccountCreationDto;
import com.finance.data.domain.accounts.dto.TradingAccountDto;
import com.finance.data.mapper.accounts.TradingAccountMapper;
import com.finance.data.service.account.TradingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/finance")
public class UserTradingAccountController {
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
}
