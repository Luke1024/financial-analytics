package com.finance.repositoryshield;

import com.finance.domain.CurrencyPair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyPairRepositoryShieldTest {

    @Autowired
    private CurrencyPairRepositoryShield shield;

    @Test
    public void testSavingNewCurrencyPair(){
        String name = generateRandomName();
        CurrencyPair currencyPair = new CurrencyPair(name);

        boolean isSaved = shield.save(currencyPair, false);

        Assert.assertEquals(true, isSaved);
        Assert.assertEquals(name, shield.findByCurrencyName(name).get().getCurrencyPairName());

        try {
            shield.deleteById(currencyPair.getId());
        } catch (Exception e){}
    }

    @Test
    public void testSavingRepeatingCurrencyPair(){
        String name = generateRandomName();
        CurrencyPair currencyPair = new CurrencyPair(name);

        shield.save(currencyPair, false);
        boolean savedAgain = shield.save(currencyPair, false);

        Assert.assertEquals(false, savedAgain);

        try {
            shield.deleteById(currencyPair.getId());
        } catch (Exception e){}
    }

    @Test
    public void testSavingCurrencyPairWithOverwriting(){
        String name = generateRandomName();

        CurrencyPair pair1 = new CurrencyPair(name);
        CurrencyPair pair2 = new CurrencyPair(name);

        shield.save(pair1,false);
        long id1 = pair1.getId();

        shield.save(pair2,true);
        long id2 = pair2.getId();

        Assert.assertNotEquals(id1, id2);
    }


    private String generateRandomName(){
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