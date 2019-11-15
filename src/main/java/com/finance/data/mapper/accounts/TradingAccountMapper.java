package com.finance.data.mapper.accounts;

import com.finance.data.domain.accounts.UserTradingAccount;
import com.finance.data.domain.accounts.dto.TradingAccountCreationDto;
import com.finance.data.domain.accounts.dto.TradingAccountDto;
import com.finance.data.service.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TradingAccountMapper {
    @Autowired
    private UserTradingAccountHistoryPointMapper userTradingAccountHistoryPointMapper;

    @Autowired
    private UserService userService;

    public List<TradingAccountDto> mapToTradingDtoList(List<UserTradingAccount> userTradingAccounts){
        return userTradingAccounts.stream()
                .map(userTradingAccount -> new TradingAccountDto(userTradingAccount.getAmount(),
                userTradingAccountHistoryPointMapper(userTradingAccount.getAmount(), userTradingAccount.getPoints())))
                .collect(Collectors.toList());
    }

    public UserTradingAccount mapToTradingAccount(TradingAccountCreationDto tradingAccountCreationDto){
        return new UserTradingAccount(.ge);
    }
}
