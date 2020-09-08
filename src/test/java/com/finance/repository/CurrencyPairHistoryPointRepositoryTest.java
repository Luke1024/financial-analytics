package com.finance.repository;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class CurrencyPairHistoryPointRepositoryTest {

    @Autowired
    private CurrencyPairHistoryPointRepository currencyPairHistoryPointRepository;

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    @Test
    public void testFindPointByDate(){

        CurrencyPair currencyPair = new CurrencyPair(generateRandomString());

        currencyPairRepository.save(currencyPair);

        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 1, 1, 13,0);
        LocalDateTime localDateTime2 = LocalDateTime.of(2020, 1, 2, 13,0);
        LocalDateTime localDateTime3 = LocalDateTime.of(2020, 1, 2, 15,0);

        CurrencyPairDataPoint currencyPairDataPoint1 = new CurrencyPairDataPoint(localDateTime1,1.0, currencyPair);
        CurrencyPairDataPoint currencyPairDataPoint2 = new CurrencyPairDataPoint(localDateTime2,1.5, currencyPair);
        CurrencyPairDataPoint currencyPairDataPoint3 = new CurrencyPairDataPoint(localDateTime3,2.0, currencyPair);

        currencyPair.addDataPoint(Arrays.asList(currencyPairDataPoint1, currencyPairDataPoint2, currencyPairDataPoint3));

        currencyPairRepository.save(currencyPair);

        LocalDateTime searchedDate = LocalDateTime.of(2020,1,2,13,0,0);

        Assert.assertEquals(searchedDate.toString(), currencyPairHistoryPointRepository.findPointByDate(
                LocalDateTime.of(2020,1,2,13,0,0),
                currencyPair.getId()).get().getTimeStamp().toString());

        currencyPairHistoryPointRepository.delete(currencyPairDataPoint1);
        currencyPairHistoryPointRepository.delete(currencyPairDataPoint2);
        currencyPairHistoryPointRepository.delete(currencyPairDataPoint3);

        currencyPairRepository.deleteById(currencyPair.getId());
    }

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
}