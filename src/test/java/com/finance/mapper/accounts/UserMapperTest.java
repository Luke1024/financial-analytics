package com.finance.mapper.accounts;

import com.finance.domain.UserData;
import com.finance.domain.TradingAccount;
import com.finance.domain.User;
import com.finance.domain.dto.PersonalDataDto;
import com.finance.domain.dto.UserRegistrationDto;
import com.finance.mapper.PersonalDataMapper;
import com.finance.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserMapperTest {

    @Autowired
    private PersonalDataMapper personalDataMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    void mapToUser() {
        List<TradingAccount> tradingAccountList = new ArrayList<>();

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(new PersonalDataDto(),
                "password", "email");

        User userMapped = userMapper.mapToUser(userRegistrationDto);

        User user = new User(new UserData(), "password", "email", tradingAccountList,
                false, userMapped.getRegistrationDate());

        assertThat(user, sameBeanAs(userMapped));
    }
}