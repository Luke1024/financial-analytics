package com.finance.mapper;

import com.finance.domain.TradingAccount;
import com.finance.domain.User;
import com.finance.domain.dto.TradingAccountCreationDto;
import com.finance.domain.dto.TradingAccountDto;
import com.finance.service.UserService;
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
                        account.getAccountType(), account.getAmount(), account.getLeverage(), account.getCreationTime(),
                        tradingAccountHistoryPointMapper.mapToAccountHistoryPointDtos(account.getPoints())))
                .collect(Collectors.toList());
    }

    public TradingAccountDto mapToTradingAccountDto(TradingAccount account){
        return new TradingAccountDto(account.getId(), account.getUser().getId(),
                account.getAccountType(), account.getAmount(), account.getLeverage(), account.getCreationTime(),
                tradingAccountHistoryPointMapper.mapToAccountHistoryPointDtos(account.getPoints()));
    }

    public TradingAccount mapToNewTradingAccount(TradingAccountCreationDto creationDto){
        User retrievedUser = userService.getUserById(creationDto.getUserId());
        if(retrievedUser != null){
            return new TradingAccount(
                    retrievedUser, creationDto.getAccountType(), 0.0, creationDto.getLeverage(),
                    LocalDateTime.now(), new ArrayList<>());
        } else {
            System.out.println("user not found");
            return new TradingAccount();
        }
    }


}
