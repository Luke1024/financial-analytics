package com.finance.data.service.account;

import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.UserTradingAccount;
import com.finance.data.repository.accounts.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class TradingAccountServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TradingAccountService tradingAccountService;

    @Test
    void createAndGetUserTradingAccounts() {
        LocalDateTime accountOpening = LocalDateTime.now();

        User user = new User("password", "email", new ArrayList<>(), false, LocalDateTime.now());

        userService.saveUser(user);

        UserTradingAccount userTradingAccount1 = new UserTradingAccount(0.0, 100, accountOpening, new ArrayList<>());
        UserTradingAccount userTradingAccount2 = new UserTradingAccount(0.0, 10, accountOpening, new ArrayList<>());

        userTradingAccount1.setUser(user);
        userTradingAccount2.setUser(user);

        tradingAccountService.createTradingAccount(userTradingAccount1);
        tradingAccountService.createTradingAccount(userTradingAccount2);

        List<UserTradingAccount> userTradingAccounts = tradingAccountService.getUserTradingAccounts(user.getId());

        Assert.assertEquals(100, userTradingAccounts.get(0).getLeverage());
        Assert.assertEquals(10, userTradingAccounts.get(1).getLeverage());

        if(user.getId() != null) {
            userRepository.deleteById(user.getId());
        }
    }
}