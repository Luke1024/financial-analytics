package com.finance.tradingDataService.service;

import com.finance.tradingDataService.domain.Currency;
import com.finance.tradingDataService.domain.CurrencyHistoryPoint;
import com.finance.tradingDataService.repository.CurrencyRepository;
import com.finance.tradingDataService.service.currency.CurrencyService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class CurrencyServiceTest {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyService currencyService;

    @Test
    public void retrieveCurrencyByKey() {
        List<CurrencyHistoryPoint> currencyHistoryPointList = new ArrayList<>();
        Currency currency = new Currency("USD", "EUR", currencyHistoryPointList);
        currencyRepository.save(currency);

        List<Currency> currencyList = currencyService.retrieveCurrencyByKey(currency.getCurrencyName());
        Assert.assertEquals("USD", currencyList.get(0).getBase());
        Assert.assertEquals("EUR", currencyList.get(0).getCurrencyName());

        System.out.println(currency.getId());
        currencyRepository.deleteById(currency.getId());
    }

    @Test
    public void addHistoryPoint() {
        List<CurrencyHistoryPoint> currencyHistoryPointList = new ArrayList<>();
        Currency currency = new Currency("USD", "EUR", currencyHistoryPointList);
        currencyRepository.save(currency);

        currencyService.addHistoryPoint("EUR", "0.4123", LocalDateTime.now());

        Optional<Currency> currencyOptional = currencyRepository.findById(currency.getId());
        System.out.println();

        Assert.assertEquals("EUR" ,currencyOptional.get().getCurrencyName());
        Assert.assertEquals(new Double(0.4123), currencyOptional.get().getCurrencyHistoryPoints().get(0).getValue());

        currencyRepository.deleteById(currency.getId());
    }

    @Test
    public void addCurrency() {
        currencyService.addCurrency("EUR", "USD");
        List<Currency> currencies = currencyRepository.findByCurrencyName("EUR");
        Assert.assertEquals("EUR", currencies.get(0).getCurrencyName());

        currencyRepository.deleteById(currencies.get(0).getId());
    }
}