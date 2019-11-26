package com.finance.data.service;

import com.finance.data.domain.currency.Currency;
import com.finance.data.domain.currency.CurrencyHistoryPoint;
import com.finance.data.domain.currency.Order;
import com.finance.data.repository.currency.CurrencyRepository;
import com.finance.data.service.currency.CurrencyService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
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
    public void getLastCurrencyHistoryPoint(){
        List<CurrencyHistoryPoint> currencyHistoryPointList = new ArrayList<>();

        LocalDateTime localDateTime1 = LocalDateTime.now().minusDays(1);
        LocalDateTime localDateTime2 = LocalDateTime.now();

        Currency currency = new Currency("HKJFKLF", "LFKHGLYR", currencyHistoryPointList);

        currencyRepository.save(currency);

        CurrencyHistoryPoint currencyHistoryPoint1 = new CurrencyHistoryPoint(localDateTime1, 1.0, currency, new Order());
        CurrencyHistoryPoint currencyHistoryPoint2 = new CurrencyHistoryPoint(localDateTime2, 2.0, currency, new Order());

        currencyHistoryPointList.add(currencyHistoryPoint1);
        currencyHistoryPointList.add(currencyHistoryPoint2);

        currency.setCurrencyHistoryPoints(currencyHistoryPointList);

        currencyRepository.save(currency);

        CurrencyHistoryPoint retrievedCurrencyHistoryPoint = currencyService.getLastCurrencyHistoryPoint("LFKHGLYR");

        Assert.assertTrue(retrievedCurrencyHistoryPoint.getTimeStamp().equals(localDateTime2));

        currencyRepository.deleteById(currency.getId());

    }

    @Test
    public void retrieveCurrencyByKey() {
        List<CurrencyHistoryPoint> currencyHistoryPointList = new ArrayList<>();
        Currency currency = new Currency("HKJFKLF", "LFKHGLYR", currencyHistoryPointList);
        currencyRepository.save(currency);

        List<Currency> currencyList = currencyService.retrieveCurrencyByKey(currency.getCurrencyName());
        Assert.assertEquals("HKJFKLF", currencyList.get(0).getBase());
        Assert.assertEquals("LFKHGLYR", currencyList.get(0).getCurrencyName());

        System.out.println(currency.getId());
        currencyRepository.deleteById(currency.getId());
    }

    @Test
    public void addHistoryPoint() {
        List<CurrencyHistoryPoint> currencyHistoryPointList = new ArrayList<>();
        Currency currency = new Currency("HKJFKLF", "LFKHGLYR", currencyHistoryPointList);
        currencyRepository.save(currency);

        currencyService.addHistoryPoint("LFKHGLYR", "0.4123", LocalDateTime.now());

        Optional<Currency> currencyOptional = currencyRepository.findById(currency.getId());
        System.out.println();

        Assert.assertEquals("LFKHGLYR" ,currencyOptional.get().getCurrencyName());
        Assert.assertEquals(new Double(0.4123), currencyOptional.get().getCurrencyHistoryPoints().get(0).getValue());

        currencyRepository.deleteById(currency.getId());
    }

    @Test
    public void addCurrency() {
        currencyService.addCurrency("HKJFKLF", "LFKHGLYR");
        List<Currency> currencies = currencyRepository.findByCurrencyName("LFKHGLYR");
        Assert.assertEquals("LFKHGLYR", currencies.get(0).getCurrencyName());

        currencyRepository.deleteById(currencies.get(0).getId());
    }
}