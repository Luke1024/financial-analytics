package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.repository.CurrencyPairRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyPairServiceTest {

    @Autowired
    private CurrencyPairService currencyPairService;

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    private final boolean authorizeDatabaseCleaning = false;

    @Test
    public void getCurrenciesEmpty(){
        if(authorizeDatabaseCleaning) {
            currencyPairDataBaseCleaning();

            String currencyName = generateRandomString();
            List<CurrencyPair> currencyPairs = currencyPairService.getCurrencies();

            Assert.assertTrue(currencyPairs.isEmpty());
        }
    }

    @Test
    public void getCurrenciesNotEmptyDeletingTest(){
        String randomName = generateRandomString();

        CurrencyPair newPair = new CurrencyPair(randomName);

        currencyPairService.saveCurrencyPair(newPair,false);

        CurrencyPair receivedCurrencyPair = currencyPairService.getCurrencyPair(randomName);

        Assert.assertEquals(newPair.toString(), receivedCurrencyPair.toString());

        currencyPairService.deleteById(newPair.getId());
        Assert.assertFalse(currencyPairRepository.findById(newPair.getId()).isPresent());
    }

    @Test
    public void getCurrencyPairNameNull(){
        CurrencyPair currencyPair = currencyPairService.getCurrencyPair(null);

        Assert.assertNull(currencyPair);
    }

    @Test
    public void getCurrencyPairNameNotNullDatabaseEmpty(){
        String randomName = generateRandomString();

        CurrencyPair newPair = new CurrencyPair(randomName);

        CurrencyPair currencyPair = currencyPairService.getCurrencyPair(randomName);

        Assert.assertNull(currencyPair);
    }


    private void currencyPairDataBaseCleaning(){
        if(authorizeDatabaseCleaning) {
            List<CurrencyPair> currencyPairList = currencyPairRepository.findAll();
            for (CurrencyPair currencyPair : currencyPairList) {
                currencyPairRepository.deleteById(currencyPair.getId());
            }
        }
    }

    private String generateRandomString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}