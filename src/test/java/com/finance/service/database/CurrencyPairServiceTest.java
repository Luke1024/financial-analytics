package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.repository.CurrencyPairRepository;
import com.finance.service.database.communicationObjects.DatabaseResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyPairServiceTest {

    @Autowired
    private CurrencyPairService currencyPairService;

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    private final boolean authorizeDatabaseCleaning = true;

    @Test
    public void getCurrenciesEmpty(){
        if(authorizeDatabaseCleaning) {
            currencyPairDataBaseCleaning();

            String currencyName = generateRandomString();
            DatabaseResponse databaseResponse = currencyPairService.getCurrencies();

            Assert.assertTrue(databaseResponse.getRequestedObjects().size() == 0 && databaseResponse.isOK() == true);
        }
    }

    @Test
    public void getCurrenciesNotEmpty(){

    }

    @Test
    public void getCurrencyPairNameNull(){

    }

    @Test
    public void getCurrencyPairNameNotNull(){

    }

    @Test
    public void getCurrencyPairNotNullDatabaseEmpty(){

    }

    @Test
    public void deleteCurrencyPairNotExist(){

    }

    @Test
    public void deleteCurrencyPairExist(){

    }

    @Test
    public void saveCurrencyPairNotNull(){

    }

    @Test
    public void saveCurrencyPairNull(){

    }

    @Test
    public void saveCurrencyPairNameNull(){

    }

    @Test
    public void saveCurrencyPairAlreadyExistsOverwritingNotAllowed(){

    }

    @Test
    public void saveCurrencyPairAlreadyExistsOverwritingAllowed(){

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