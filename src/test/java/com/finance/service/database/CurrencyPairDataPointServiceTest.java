package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.PairDataRequest;
import com.finance.domain.dto.currencypair.PointTimeFrame;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyPairDataPointServiceTest {

    @Autowired
    private CurrencyPairDataPointService currencyPairDataPointService;

    @Autowired
    private CurrencyPairService currencyPairService;

    private LocalDateTime localDateTime1 = LocalDateTime.of(2020,1,1,12,0);
    private LocalDateTime localDateTime2 = LocalDateTime.of(2020,1,1,13,0);
    private LocalDateTime localDateTime3 = LocalDateTime.of(2020,1,1,14,0);
    private LocalDateTime localDateTime4 = LocalDateTime.of(2020,1,1,15,0);
    private LocalDateTime localDateTime5 = LocalDateTime.of(2020,1,1,16,0);

    private CurrencyPair currencyPair = new CurrencyPair(generateRandomString());

    private CurrencyPairDataPoint currencyPairDataPoint1 = new CurrencyPairDataPoint(localDateTime1, 2.0, currencyPair);
    private CurrencyPairDataPoint currencyPairDataPoint2 = new CurrencyPairDataPoint(localDateTime2, 2.5, currencyPair);
    private CurrencyPairDataPoint currencyPairDataPoint3 = new CurrencyPairDataPoint(localDateTime3, 3.0, currencyPair);
    private CurrencyPairDataPoint currencyPairDataPoint4 = new CurrencyPairDataPoint(localDateTime4, 3.5, currencyPair);
    private CurrencyPairDataPoint currencyPairDataPoint5 = new CurrencyPairDataPoint(localDateTime5, 4.0, currencyPair);

    private List<CurrencyPairDataPoint> currencyPairDataPoints = new ArrayList<>(Arrays.asList(
            currencyPairDataPoint1, currencyPairDataPoint2, currencyPairDataPoint3,
            currencyPairDataPoint4, currencyPairDataPoint5));

    @Test
    public void getCurrencyPairHistory_CurrencyNameNull(){
        PairDataRequest pairDataRequest = new PairDataRequest(null, 5,PointTimeFrame.D1, 0);

        List<CurrencyPairDataPoint> receivedCurrencyPairHistory = currencyPairDataPointService.getCurrencyPairHistory(pairDataRequest);

        Assert.assertEquals(Collections.emptyList(),receivedCurrencyPairHistory);
    }

    @Test
    public void getCurrencyPairHistory_requestedNumberOfDataPointsIsZero(){
        PairDataRequest pairDataRequest = new PairDataRequest("sdasd", 0, PointTimeFrame.D1, 0);

        List<CurrencyPairDataPoint> receivedCurrencyPairHistory = currencyPairDataPointService.getCurrencyPairHistory(pairDataRequest);

        Assert.assertEquals(Collections.emptyList(), receivedCurrencyPairHistory);
    }

    @Test
    public void getCurrencyPairHistory_PointTimeFrameIsNull(){
        PairDataRequest pairDataRequest = new PairDataRequest("asfadfaf", 2, null, 0);

        List<CurrencyPairDataPoint> receivedCurrencyPairHistory = currencyPairDataPointService.getCurrencyPairHistory(pairDataRequest);

        Assert.assertEquals(Collections.emptyList(),receivedCurrencyPairHistory);
    }

    @Test
    public void getCurrencyPairHistory_CurrencyPairNotExists(){
        String randomName = generateRandomString();

        PairDataRequest pairDataRequest = new PairDataRequest(randomName, 3, PointTimeFrame.D1,0);

        List<CurrencyPairDataPoint> receivedCurrencyPairHistory = currencyPairDataPointService.getCurrencyPairHistory(pairDataRequest);

        Assert.assertEquals(Collections.emptyList(),receivedCurrencyPairHistory);
    }

    @Test
    public void getCurrencyPairHistory_Adding_Deleting(){
        String randomName = generateRandomString();

        CurrencyPair newPair = new CurrencyPair(randomName);

        currencyPairService.saveCurrencyPair(newPair, false);

        LocalDateTime localDateTime1 = LocalDateTime.of(2020,1,1,12,0);
        LocalDateTime localDateTime2 = LocalDateTime.of(2020,1,1,13,0);
        LocalDateTime localDateTime3 = LocalDateTime.of(2020,1,1,14,0);
        LocalDateTime localDateTime4 = LocalDateTime.of(2020,1,1,15,0);
        LocalDateTime localDateTime5 = LocalDateTime.of(2020,1,1,16,0);

        CurrencyPairDataPoint currencyPairDataPoint1 = new CurrencyPairDataPoint(localDateTime1, 2.0, newPair);
        CurrencyPairDataPoint currencyPairDataPoint2 = new CurrencyPairDataPoint(localDateTime2, 2.5, newPair);
        CurrencyPairDataPoint currencyPairDataPoint3 = new CurrencyPairDataPoint(localDateTime3, 3.0, newPair);
        CurrencyPairDataPoint currencyPairDataPoint4 = new CurrencyPairDataPoint(localDateTime4, 3.5, newPair);
        CurrencyPairDataPoint currencyPairDataPoint5 = new CurrencyPairDataPoint(localDateTime5, 4.0, newPair);

        List<CurrencyPairDataPoint> dataPointsToSave = Arrays.asList(
                currencyPairDataPoint1,
                currencyPairDataPoint2,
                currencyPairDataPoint3,
                currencyPairDataPoint4,
                currencyPairDataPoint5);

        currencyPairDataPointService.addDataPoints(dataPointsToSave, randomName);

        PairDataRequest pairDataRequest = new PairDataRequest(randomName, 5, PointTimeFrame.H1, 0);

        List<CurrencyPairDataPoint> receivedPairDataPoints =
                currencyPairDataPointService.getCurrencyPairHistory(pairDataRequest);
        Assert.assertEquals(dataPointsToSave.stream().map(point -> point.toString()).collect(Collectors.joining()),
                receivedPairDataPoints.stream().map(point -> point.toString()).collect(Collectors.joining()));
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