package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.preprocessor.utilities.DataPoint;
import com.finance.repository.CurrencyPairRepository;
import com.finance.service.database.communicationObjects.DatabaseResponse;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    public void getCurrenciesNotEmptyDeletingTest(){
        String randomName = generateRandomString();
        if(isPairExist(randomName)){
            getCurrenciesNotEmptyDeletingTest();
        }

        CurrencyPair newPair = new CurrencyPair(randomName);

        currencyPairService.saveCurrencyPair(newPair,false);

        DatabaseResponse databaseResponse = currencyPairService.getCurrencyPair(randomName);

        Assert.assertEquals(true, databaseResponse.isOK());
        Assert.assertEquals(1, databaseResponse.getRequestedObjects().size());
        Assert.assertEquals(newPair.toString(), databaseResponse.getRequestedObjects().get(0).toString());

        DatabaseResponse deletingResponse = currencyPairService.deleteById(newPair.getId());
        Assert.assertTrue(deletingResponse.getLog().contains("removed"));
        Assert.assertFalse(currencyPairRepository.findById(newPair.getId()).isPresent());
    }

    @Test
    public void getCurrencyPairNameNull(){
        DatabaseResponse response = currencyPairService.getCurrencyPair(null);

        Assert.assertEquals(false, response.isOK());
        Assert.assertEquals("CurrencyPairName is null." ,response.getLog());
    }

    @Test
    public void getCurrencyPairNameNotNullDatabaseEmpty(){
        String randomName = generateRandomString();
        if(isPairExist(randomName)){
            getCurrencyPairNameNotNullDatabaseEmpty();
        }

        CurrencyPair newPair = new CurrencyPair(randomName);

        DatabaseResponse response = currencyPairService.getCurrencyPair(randomName);

        Assert.assertEquals(false, response.isOK());
        Assert.assertEquals("CurrencyPair not found.", response.getLog());
    }

    @Test
    public void deleteCurrencyPairNotExist(){
        long randomId = getRandomId();
        if (currencyPairRepository.findById(randomId).isPresent()) {
            deleteCurrencyPairNotExist();
        }

        DatabaseResponse response = currencyPairService.deleteById(randomId);

        Assert.assertEquals(false, response.isOK());
        Assert.assertEquals("CurrencyPair not found.", response.getLog());
    }

    @Test
    public void saveCurrencyPairNull(){
        CurrencyPair pairNull = null;

        DatabaseResponse response = currencyPairService.saveCurrencyPair(pairNull, false);

        Assert.assertEquals(false, response.isOK());
        Assert.assertEquals("CurrencyPair is null.", response.getLog());
    }

    @Test
    public void saveCurrencyPairNameNull(){
        CurrencyPair pairNameNull = new CurrencyPair();

        DatabaseResponse response = currencyPairService.saveCurrencyPair(pairNameNull, false);

        Assert.assertEquals(false, response.isOK());
        Assert.assertEquals("CurrencyPair name is null.", response.getLog());
    }

    @Test
    public void saveCurrencyPairAlreadyExistsOverwritingNotAllowed(){
        String randomName = generateRandomString();
        if(isPairExist(randomName)){
            saveCurrencyPairAlreadyExistsOverwritingAllowed();
        }

        CurrencyPair newPair = new CurrencyPair(randomName);

        DatabaseResponse response1 = currencyPairService.saveCurrencyPair(newPair, false);

        Assert.assertEquals(true, response1.isOK());

        DatabaseResponse response2 = currencyPairService.saveCurrencyPair(newPair, false);

        Assert.assertEquals(false, response2.isOK());
        Assert.assertEquals("CurrencyPair exists, overwriting is not allowed.", response2.getLog());

        DatabaseResponse deletingResponse = currencyPairService.deleteById(newPair.getId());
        Assert.assertTrue(deletingResponse.getLog().contains("removed"));
        Assert.assertFalse(currencyPairRepository.findById(newPair.getId()).isPresent());
    }

    @Test
    public void saveCurrencyPairAlreadyExistsOverwritingAllowed(){
        String randomName = generateRandomString();
        //if(isPairExist(randomName)){
          //  saveCurrencyPairAlreadyExistsOverwritingAllowed();
        //}

        CurrencyPair firstPair = new CurrencyPair(randomName);
        DatabaseResponse response1 = currencyPairService.saveCurrencyPair(firstPair, false);
        Assert.assertEquals(true, response1.isOK());

        System.out.println("First pair " + firstPair.getId());


        CurrencyPair secondPair = new CurrencyPair(randomName);

        //CurrencyPairDataPoint dataPoint = new CurrencyPairDataPoint(LocalDateTime.now(), 1.0, secondPair);

        //secondPair.setCurrencyPairDataPoints(Arrays.asList(dataPoint));

        DatabaseResponse response2 = currencyPairService.saveCurrencyPair(secondPair, true);

        System.out.println("Second pair " + secondPair.getId());

        System.out.println(secondPair.getCurrencyPairDataPoints().size());

        Assert.assertEquals(true, response2.isOK());
        Assert.assertEquals("CurrencyPair overwritten. CurrencyPair saved.", response2.getLog());

        DatabaseResponse response3 = currencyPairService.getCurrencyPair(randomName);

        List<CurrencyPair> currencyPairs = response3.getRequestedObjects()
                .stream().map(entity -> (CurrencyPair) entity).collect(Collectors.toList());

        Assert.assertNotEquals(firstPair.getId(), currencyPairs.get(0).getId());
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

    private boolean isPairExist(String name){
        DatabaseResponse response = currencyPairService.getCurrencyPair(name);
        try {
            if(response.getRequestedObjects().size()>0){
                return true;
            }
        } catch (Exception e){
            return false;
        }
        return false;
    }

    private long getRandomId(){
        long leftLimit = 1L;
        long rightLimit = 10L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}