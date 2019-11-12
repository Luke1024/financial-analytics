package com.finance.data.service.currency;

import com.finance.data.domain.CurrencyHistoryPoint;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        //addBaseCurrencyPoints();
        //addCurrencyPoints();
        //cleanUpDatabase();
    }

    private void addBaseCurrencyPoints() {

    }
}