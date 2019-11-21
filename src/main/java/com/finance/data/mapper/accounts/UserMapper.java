package com.finance.data.mapper.accounts;

import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.UserTradingAccount;
import com.finance.data.domain.accounts.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    @Autowired
    private PersonalDataMapper personalDataMapper;

    public User mapToUser(UserRegistrationDto userRegistrationDto) {
        List<UserTradingAccount> tradingAccountList = new ArrayList<>();

        return new User(personalDataMapper.mapToPersonalData(userRegistrationDto.getPersonalDataDto()),
                userRegistrationDto.getPassword(),
                userRegistrationDto.getEmail(),
                tradingAccountList,
                false,
                LocalDateTime.now());
    }
}
