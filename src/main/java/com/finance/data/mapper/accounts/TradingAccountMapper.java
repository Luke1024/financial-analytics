package com.finance.data.mapper.accounts;

import com.finance.data.domain.accounts.TradingAccount;
import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.dto.TradingAccountCreationDto;
import com.finance.data.domain.accounts.dto.TradingAccountDto;
import com.finance.data.service.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TradingAccountMapper {
    @Autowired
    private TradingAccountHistoryPointMapper tradingAccountHistoryPointMapper;

    @Autowired
    private UserService userService;

    public List<TradingAccountDto> mapToTradingDtoList(List<TradingAccount> tradingAccounts){
        return tradingAccounts.stream()
                .map(account -> new TradingAccountDto(account.getId(), account.getUser().getId(),
                        account.getAccountType(), account.getAmount(), account.getLeverage(), account.getOpeningTime(),
                        tradingAccountHistoryPointMapper.mapToTradingHistoryPointDtoList(account.getPoints())))
                .collect(Collectors.toList());
    }

    public TradingAccount mapToNewTradingAccount(TradingAccountCreationDto creationDto){
        User retrievedUser = userService.getUserById(creationDto.getUserId());
        if(retrievedUser != null){
            TradingAccount tradingAccount = new TradingAccount(
                    retrievedUser, creationDto.getAccountType(), 0.0, creationDto.getLeverage(),
                    LocalDateTime.now(), new ArrayList<>());
            return tradingAccount;
        } else {
            System.out.println("user not found");
            return new TradingAccount();
        }
    }
}
