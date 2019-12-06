package com.finance.data.service.currency;

import com.finance.data.domain.currency.Currency;
import com.finance.data.domain.currency.CurrencyHistoryPoint;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyPairServiceTest {

    @Autowired
    private CurrencyService currencyService;

    private List<CurrencyHistoryPoint> baseCurrencyHistoryPoints = new ArrayList<>();
    private List<CurrencyHistoryPoint> currencyHistoryPoints = new ArrayList<>();

    @Test
    void getCurrencyPairHistory() {
        LocalDateTime localDateTime1 = LocalDateTime.now().minusDays(4).withNano(0);
        LocalDateTime localDateTime2 = LocalDateTime.now().minusDays(3).withNano(0);
        LocalDateTime localDateTime3 = LocalDateTime.now().minusDays(2).withNano(0);
        LocalDateTime localDateTime4 = LocalDateTime.now().minusDays(1).withNano(0);
        LocalDateTime localDateTime5 = LocalDateTime.now().withNano(0);

        CurrencyHistoryPoint baseCurrencyHistoryPoint = new CurrencyHistoryPoint(localDateTime1, 2.0, new Currency());
    }
}