package com.finance.service;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairHistoryPoint;
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
    /*
    @Autowired
    private CurrencyPair currencyPair;

    @Autowired
    private CurrencyService currencyService;


    @Test
    public void getLastCurrencyHistoryPoint(){
        List<CurrencyPairHistoryPoint> currencyPairHistoryPointList = new ArrayList<>();

        LocalDateTime localDateTime1 = LocalDateTime.now().minusDays(1).withNano(0);
        LocalDateTime localDateTime2 = LocalDateTime.now().withNano(0);

        CurrencyPair currency = new CurrencyPair("HKJFKLF", "LFKHGLYR");

        CurrencyPairHistoryPoint currencyPairHistoryPoint1 = new CurrencyPairHistoryPoint(localDateTime1, 1.0, currency);
        CurrencyPairHistoryPoint currencyPairHistoryPoint2 = new CurrencyPairHistoryPoint(localDateTime2, 2.0, currency);

        currencyPairHistoryPointList.add(currencyPairHistoryPoint1);
        currencyPairHistoryPointList.add(currencyPairHistoryPoint2);

        currency.setCurrencyPairHistoryPoints(currencyPairHistoryPointList);

        currencySavingBlock(currency);

        System.out.println(currency.getId());

        System.out.println("Currency history points size:" + currency.getCurrencyPairHistoryPoints().size());

        CurrencyPairHistoryPoint retrievedCurrencyPairHistoryPoint = currencyService.getLastCurrencyHistoryPoint("LFKHGLYR");

        System.out.println(retrievedCurrencyPairHistoryPoint.getTimeStamp() + " " + localDateTime2);

        Assert.assertTrue(retrievedCurrencyPairHistoryPoint.getTimeStamp().equals(localDateTime2));

        currencyPair.deleteById(currency.getId());

    }

    @Test
    public void retrieveCurrencyByKey() {
        List<CurrencyPairHistoryPoint> currencyPairHistoryPointList = new ArrayList<>();
        CurrencyPair currency = new CurrencyPair("HKJFKLF", "LFKHGLYR");

        currencySavingBlock(currency);

        List<CurrencyPair> currencyList = currencyService.retrieveCurrencyByKey(currency.getCurrencyPairName());
        Assert.assertEquals("HKJFKLF", currencyList.get(0).getBase());
        Assert.assertEquals("LFKHGLYR", currencyList.get(0).getCurrencyPairName());

        System.out.println(currency.getId());
        currencyPair.deleteById(currency.getId());
    }

    @Test
    public void addHistoryPoint() {
        List<CurrencyPairHistoryPoint> currencyPairHistoryPointList = new ArrayList<>();
        CurrencyPair currency = new CurrencyPair("HKJFKLF", "LFKHGLYR");
        currencySavingBlock(currency);

        currencyService.addHistoryPoint("LFKHGLYR", "0.4123", LocalDateTime.now());

        Optional<CurrencyPair> currencyOptional = currencyPair.findById(currency.getId());
        System.out.println();

        Assert.assertEquals("LFKHGLYR" ,currencyOptional.get().getCurrencyPairName());
        Assert.assertEquals(new Double(0.4123), currencyOptional.get().getCurrencyPairHistoryPoints().get(0).getValue());

        currencyPair.deleteById(currency.getId());
    }

    @Test
    public void addCurrency() {
        currencyService.addCurrency("HKJFKLF", "LFKHGLYR");
        List<CurrencyPair> currencies = currencyPair.findByCurrencyName("LFKHGLYR");
        Assert.assertEquals("LFKHGLYR", currencies.get(0).getCurrencyPairName());

        currencyPair.deleteById(currencies.get(0).getId());
    }

    private void currencySavingBlock(CurrencyPair currency){
        List<CurrencyPair> retrievedCurrency = currencyPair.retrieveByBaseAndName(
                currency.getBase(), currency.getCurrencyPairName());

        if(retrievedCurrency.isEmpty()){
            currencyPair.save(currency);
        } else {
            currencyPair.deleteById(retrievedCurrency.get(0).getId());
            currencyPair.save(currency);
        }
    }
     */
}