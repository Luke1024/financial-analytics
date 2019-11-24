package com.finance.data.mapper.accounts;

import com.finance.data.domain.accounts.OperationType;
import com.finance.data.domain.accounts.TradingAccount;
import com.finance.data.domain.accounts.TradingAccountHistoryPoint;
import com.finance.data.domain.accounts.dto.TradingAccountHistoryPointDto;
import com.finance.data.domain.currency.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

@RunWith(SpringRunner.class)
@SpringBootTest
class TradingAccountHistoryPointMapperTest {

    @Autowired
    private TradingAccountHistoryPointMapper tradingAccountHistoryPointMapper;

    @Test
    void mapToAccountHistoryPointDtos() {
        LocalDateTime localDateTime = LocalDateTime.now();

        TradingAccountHistoryPoint tradingAccountHistoryPoint1 =
                new TradingAccountHistoryPoint(1L, OperationType.TRADE, 0.0, 0.0,
                        0.0, localDateTime, new TradingAccount(1L), new Order(1L));

        TradingAccountHistoryPointDto tradingAccountHistoryPointDto1 =
                new TradingAccountHistoryPointDto(1L, OperationType.TRADE, 0.0, 0.0,
                        0.0, localDateTime, 1L,1L);

        TradingAccountHistoryPoint tradingAccountHistoryPoint2 =
                new TradingAccountHistoryPoint(2L,OperationType.PAYMENT, 0.0, 0.0,
                        0.0, localDateTime, new TradingAccount(2L), new Order(2L));

        TradingAccountHistoryPointDto tradingAccountHistoryPointDto2 =
                new TradingAccountHistoryPointDto(2L,OperationType.PAYMENT, 0.0, 0.0,
                        0.0, localDateTime, 2L, 2L);

        List<TradingAccountHistoryPoint> tradingAccountHistoryPointList = new ArrayList<>();
        List<TradingAccountHistoryPointDto> tradingAccountHistoryPointDtos = new ArrayList<>();

        tradingAccountHistoryPointList.add(tradingAccountHistoryPoint1);
        tradingAccountHistoryPointList.add(tradingAccountHistoryPoint2);

        tradingAccountHistoryPointDtos.add(tradingAccountHistoryPointDto1);
        tradingAccountHistoryPointDtos.add(tradingAccountHistoryPointDto2);

        assertThat(tradingAccountHistoryPointDtos,
                sameBeanAs(tradingAccountHistoryPointMapper.mapToAccountHistoryPointDtos(tradingAccountHistoryPointList)));
    }
}