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
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CurrencyPairRepositoryTest {

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    @Test
    public void testSearchingByCurrencyName(){
        CurrencyPair currencyPair1 = new CurrencyPair("EUR/USD");
        CurrencyPair currencyPair2 = new CurrencyPair("GBP/USD");

        checkerDeleter(currencyPair1);
        checkerDeleter(currencyPair2);

        currencyPairRepository.save(currencyPair1);
        currencyPairRepository.save(currencyPair2);

        Assert.assertEquals(currencyPair1.toString(),currencyPairRepository.findByCurrencyName("EUR/USD").get().toString());

        currencyPairRepository.deleteById(currencyPair1.getId());
        currencyPairRepository.deleteById(currencyPair2.getId());
    }

    @Test
    public void testFindAll(){
        CurrencyPair currencyPair1 = new CurrencyPair("EUR/USD");
        CurrencyPair currencyPair2 = new CurrencyPair("GBP/USD");

        checkerDeleter(currencyPair1);
        checkerDeleter(currencyPair2);

        currencyPairRepository.save(currencyPair1);
        currencyPairRepository.save(currencyPair2);

        List<CurrencyPair> expected = Arrays.asList(currencyPair1, currencyPair2);

        Assert.assertEquals(expected.stream().map(pair -> pair.toString()).collect(Collectors.joining()),
                currencyPairRepository.findAll().stream().map(pair -> pair.toString()).collect(Collectors.joining()));

        currencyPairRepository.deleteById(currencyPair1.getId());
        currencyPairRepository.deleteById(currencyPair2.getId());
    }

    public void checkerDeleter(CurrencyPair currencyPair){
        Optional<CurrencyPair> pair = currencyPairRepository.findByCurrencyName(currencyPair.getCurrencyPairName());
        if(pair.isPresent()){
            currencyPairRepository.deleteById(pair.get().getId());
        }
    }
}