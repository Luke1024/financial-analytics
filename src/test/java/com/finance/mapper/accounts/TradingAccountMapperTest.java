package com.finance.mapper.accounts;

import com.finance.domain.TradingAccount;
import com.finance.domain.TradingAccountHistoryPoint;
import com.finance.domain.User;
import com.finance.domain.dto.TradingAccountCreationDto;
import com.finance.domain.dto.TradingAccountDto;
import com.finance.domain.dto.TradingAccountHistoryPointDto;
import com.finance.domain.enums.AccountType;
import com.finance.domain.enums.OperationType;
import com.finance.domain.Order;
import com.finance.mapper.TradingAccountMapper;
import com.finance.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class TradingAccountMapperTest {
    @Autowired
    private TradingAccountMapper tradingAccountMapper;

    @Autowired
    private UserService userService;

    @Test
    void mapToTradingDtoList() {
        LocalDateTime accountOpening = LocalDateTime.now();

        TradingAccountHistoryPoint tradingAccountHistoryPoint1 =
                new TradingAccountHistoryPoint(1L, OperationType.TRADE, 0.0, 0.0,
                        0.0, accountOpening, new TradingAccount(1L), new Order(1L));

        TradingAccountHistoryPointDto tradingAccountHistoryPointDto1 =
                new TradingAccountHistoryPointDto(1L, OperationType.TRADE, 0.0, 0.0,
                        0.0, accountOpening, 1L,1L);

        TradingAccountHistoryPoint tradingAccountHistoryPoint2 =
                new TradingAccountHistoryPoint(2L,OperationType.PAYMENT, 0.0, 0.0,
                        0.0, accountOpening, new TradingAccount(2L), new Order(2L));

        TradingAccountHistoryPointDto tradingAccountHistoryPointDto2 =
                new TradingAccountHistoryPointDto(2L,OperationType.PAYMENT, 0.0, 0.0,
                        0.0, accountOpening, 2L, 2L);

        List<TradingAccountHistoryPoint> tradingAccountHistoryPoints = new ArrayList<>();

        List<TradingAccountHistoryPointDto> tradingAccountHistoryPointDtos = new ArrayList<>();

        tradingAccountHistoryPoints.add(tradingAccountHistoryPoint1);
        tradingAccountHistoryPoints.add(tradingAccountHistoryPoint2);

        tradingAccountHistoryPointDtos.add(tradingAccountHistoryPointDto1);
        tradingAccountHistoryPointDtos.add(tradingAccountHistoryPointDto2);

        TradingAccount tradingAccount1 = new TradingAccount(1L,new User(1L), AccountType.DEMO,0.0,
                10,accountOpening, tradingAccountHistoryPoints);
        TradingAccount tradingAccount2 = new TradingAccount(2L, new User(2L), AccountType.REAL,0.0,
                100, accountOpening, tradingAccountHistoryPoints);

        TradingAccountDto tradingAccountDto1 = new TradingAccountDto(1L, 1L, AccountType.DEMO, 0.0,
                10, accountOpening, tradingAccountHistoryPointDtos);
        TradingAccountDto tradingAccountDto2 = new TradingAccountDto(2L, 2L, AccountType.REAL, 0.0,
                100, accountOpening, tradingAccountHistoryPointDtos);

        List<TradingAccount> tradingAccounts = new ArrayList<>();
        tradingAccounts.add(tradingAccount1);
        tradingAccounts.add(tradingAccount2);

        List<TradingAccountDto> tradingAccountDtos = new ArrayList<>();
        tradingAccountDtos.add(tradingAccountDto1);
        tradingAccountDtos.add(tradingAccountDto2);

        assertThat(tradingAccountDtos, sameBeanAs(tradingAccountMapper.mapToTradingDtoList(tradingAccounts)));
    }

    @Test
    void mapToNewTradingAccount() {
        User user = new User();

        userService.saveUser(user);

        TradingAccountCreationDto tradingAccountCreationDto =
                new TradingAccountCreationDto(user.getId(), AccountType.REAL, 100);

        TradingAccount tradingAccount = new TradingAccount(user, AccountType.REAL, 0.0, 100,
                LocalDateTime.now(), new ArrayList<>());

        TradingAccount tradingAccountMapped = tradingAccountMapper.mapToNewTradingAccount(tradingAccountCreationDto);

        Assert.assertTrue(tradingAccountMapped.getUser().getId()==user.getId() &&
                tradingAccountMapped.getAccountType()==tradingAccount.getAccountType() &&
                tradingAccountMapped.getAmount()==tradingAccount.getAmount() &&
                tradingAccountMapped.getLeverage()==tradingAccount.getLeverage());

        if(user.getId() != null) {
            userService.deleteUserById(user.getId());
        }
    }
}