package com.finance.repository;

import com.finance.domain.CurrencyPair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CurrencyPairRepositoryTest {

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    @Test
    public void testSearchingByCurrencyName(){
        String name1 = generateRandomString();
        String name2 = generateRandomString();
        CurrencyPair currencyPair1 = new CurrencyPair(name1);
        CurrencyPair currencyPair2 = new CurrencyPair(name2);

        checkerDeleter(currencyPair1);
        checkerDeleter(currencyPair2);

        currencyPairRepository.save(currencyPair1);
        currencyPairRepository.save(currencyPair2);

        Assert.assertEquals(currencyPair1.toString(),currencyPairRepository.findByCurrencyName(name1).get().toString());

        currencyPairRepository.deleteById(currencyPair1.getId());
        currencyPairRepository.deleteById(currencyPair2.getId());
    }
/*
    @Test
    public void testFindAll(){
        CurrencyPair currencyPair1 = new CurrencyPair("EUR/USD");
        CurrencyPair currencyPair2 = new CurrencyPair("GBP/USD");

        checkerDeleter(currencyPair1);
        checkerDeleter(currencyPair2);

        currencyPairRepository.save(currencyPair1);
        currencyPairRepository.save(currencyPair2);

        List<CurrencyPair> expected = Arrays.asList(currencyPair1, currencyPair2);
        List<CurrencyPair> receivedPairs = currencyPairRepository.findAll();

        String expectedInString = expected.stream().map(pair -> pair.toString()).collect(Collectors.joining());
        String receivedPairsInString = receivedPairs.stream().map(pair -> pair.toString()).collect(Collectors.joining());

        Assert.assertEquals(expectedInString, receivedPairsInString);

        currencyPairRepository.deleteById(currencyPair1.getId());
        currencyPairRepository.deleteById(currencyPair2.getId());
    }
*/
    private String generateRandomString(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }

    public void checkerDeleter(CurrencyPair currencyPair){
        Optional<CurrencyPair> pair = currencyPairRepository.findByCurrencyName(currencyPair.getCurrencyPairName());
        if(pair.isPresent()){
            currencyPairRepository.deleteById(pair.get().getId());
        }
    }
}