package com.finance.data.service.account;

import com.finance.data.domain.accounts.AccountType;
import com.finance.data.domain.accounts.TradingAccount;
import com.finance.data.domain.accounts.User;
import com.finance.data.repository.accounts.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;

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
    private TradingAccountService tradingAccountService;

    @Test
    void createAndGetUserTradingAccounts() {
        LocalDateTime accountOpening = LocalDateTime.now();

        User user = new User("password", "email", new ArrayList<>(), false, LocalDateTime.now());

        userService.saveUser(user);

        TradingAccount tradingAccount1 = new TradingAccount(user, AccountType.REAL,0.0, 100,
                accountOpening, new ArrayList<>());
        TradingAccount tradingAccount2 = new TradingAccount(user, AccountType.REAL ,0.0, 10,
                accountOpening, new ArrayList<>());

        tradingAccountService.createTradingAccount(tradingAccount1);
        tradingAccountService.createTradingAccount(tradingAccount2);

        List<TradingAccount> tradingAccounts = tradingAccountService.getUserTradingAccounts(user.getId());

        Assert.assertEquals(100, tradingAccounts.get(0).getLeverage());
        Assert.assertEquals(10, tradingAccounts.get(1).getLeverage());

        if(user.getId() != null) {
            userService.deleteUserById(user.getId());
        }
    }
}