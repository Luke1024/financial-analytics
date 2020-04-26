package com.finance.repository;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CurrencyPairHistoryPointRepositoryTest {

    @Autowired
    private CurrencyPairHistoryPointRepository currencyPairHistoryPointRepository;

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    @Test
    public void testLastDataPoint(){
        CurrencyPair currencyPair = new CurrencyPair("EUR/USD");

        currencyPairRepository.save(currencyPair);

        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 1, 1, 13,0);
        LocalDateTime localDateTime2 = LocalDateTime.of(2020, 1, 2, 13,0);
        LocalDateTime localDateTime3 = LocalDateTime.of(2020, 1, 2, 15,0);

        CurrencyPairDataPoint currencyPairDataPoint1 = new CurrencyPairDataPoint(localDateTime1,1.0, currencyPair);
        CurrencyPairDataPoint currencyPairDataPoint2 = new CurrencyPairDataPoint(localDateTime2,1.5, currencyPair);
        CurrencyPairDataPoint currencyPairDataPoint3 = new CurrencyPairDataPoint(localDateTime3,2.0, currencyPair);

        currencyPairHistoryPointRepository.save(currencyPairDataPoint1);
        currencyPairHistoryPointRepository.save(currencyPairDataPoint2);
        currencyPairHistoryPointRepository.save(currencyPairDataPoint3);

        Assert.assertEquals(currencyPairDataPoint3.getPointId(),
                currencyPairHistoryPointRepository.getLastDataPoint(currencyPair.getId()));

        currencyPairHistoryPointRepository.delete(currencyPairDataPoint1);
        currencyPairHistoryPointRepository.delete(currencyPairDataPoint2);
        currencyPairHistoryPointRepository.delete(currencyPairDataPoint3);

        currencyPairRepository.deleteById(currencyPair.getId());
    }

    @Test
    public void testFindPointByDate(){

        CurrencyPair currencyPair = new CurrencyPair("EUR/USD");

        currencyPairRepository.save(currencyPair);

        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 1, 1, 13,0);
        LocalDateTime localDateTime2 = LocalDateTime.of(2020, 1, 2, 13,0);
        LocalDateTime localDateTime3 = LocalDateTime.of(2020, 1, 2, 15,0);

        CurrencyPairDataPoint currencyPairDataPoint1 = new CurrencyPairDataPoint(localDateTime1,1.0, currencyPair);
        CurrencyPairDataPoint currencyPairDataPoint2 = new CurrencyPairDataPoint(localDateTime2,1.5, currencyPair);
        CurrencyPairDataPoint currencyPairDataPoint3 = new CurrencyPairDataPoint(localDateTime3,2.0, currencyPair);

        currencyPairHistoryPointRepository.save(currencyPairDataPoint1);
        currencyPairHistoryPointRepository.save(currencyPairDataPoint2);
        currencyPairHistoryPointRepository.save(currencyPairDataPoint3);

        LocalDateTime searchedDate = LocalDateTime.of(2020,1,2,13,0,0);

        Assert.assertEquals(searchedDate.toString(), currencyPairHistoryPointRepository.findPointByDate(
                LocalDateTime.of(2020,1,2,13,0,0),
                currencyPair.getId()).get().getTimeStamp().toString());

        currencyPairHistoryPointRepository.delete(currencyPairDataPoint1);
        currencyPairHistoryPointRepository.delete(currencyPairDataPoint2);
        currencyPairHistoryPointRepository.delete(currencyPairDataPoint3);

        currencyPairRepository.deleteById(currencyPair.getId());
    }
}