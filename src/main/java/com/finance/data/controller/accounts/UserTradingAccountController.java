package com.finance.data.controller.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return tradingAccountMapper.mapToTradingDtoList(tradingAccountMapper.getUserTradingAccounts(userId));
    }

    @PostMapping(value = "/tradingAccount", consumes = APPLICATION_JSON_VALUE)
    public void createUserTradingAccount(@RequestBody TradingAccountCreationDto tradingAccountCreationDto) {
        tradingAccountService.createTradingAccount(tradingAccountCreationDto);
    }

    @PutMapping(value = "/tradingAccount", consumes = APPLICATION_JSON_VALUE)
    public void updateTradingAccount(@RequestBody TradingAccountUpdaterDto tradingAccountUpdaterDto) {
        tradingAccountService.updateTradingAccount(tradingAccountUpdaterDto);
    }
}
