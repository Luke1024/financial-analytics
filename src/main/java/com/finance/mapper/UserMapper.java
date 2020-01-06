package com.finance.mapper;

import com.finance.domain.User;
import com.finance.domain.TradingAccount;
import com.finance.domain.dto.UserRegistrationDto;
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
        List<TradingAccount> tradingAccountList = new ArrayList<>();

        return new User(personalDataMapper.mapToPersonalData(userRegistrationDto.getPersonalDataDto()),
                userRegistrationDto.getPassword(),
                userRegistrationDto.getEmail(),
                tradingAccountList,
                false,
                LocalDateTime.now());
    }
}
