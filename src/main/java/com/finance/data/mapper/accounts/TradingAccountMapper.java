package com.finance.data.mapper.accounts;

import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.UserTradingAccount;
import com.finance.data.domain.accounts.dto.TradingAccountCreationDto;
import com.finance.data.domain.accounts.dto.TradingAccountDto;
import com.finance.data.repository.accounts.UserRepository;
import com.finance.data.service.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TradingAccountMapper {
    @Autowired
    private UserTradingAccountHistoryPointMapper userTradingAccountHistoryPointMapper;

    @Autowired
    private UserRepository userRepository;

    public List<TradingAccountDto> mapToTradingDtoList(List<UserTradingAccount> userTradingAccounts){
        return userTradingAccounts.stream()
                .map(userTradingAccount -> new TradingAccountDto(userTradingAccount.getAmount(),
                userTradingAccountHistoryPointMapper.mapToTradingHistoryPointList(userTradingAccount.getPoints())))
                .collect(Collectors.toList());
    }

    public UserTradingAccount mapToNewTradingAccount(TradingAccountCreationDto tradingAccountCreationDto){
        Optional<User> retrievedUser = userRepository.findById(tradingAccountCreationDto.getUserId());
        if(retrievedUser.isPresent()){
            return new UserTradingAccount(retrievedUser.get(), 0.0, new ArrayList<>());
        } else {
            //catch exception
            return new UserTradingAccount();
        }
    }
}
