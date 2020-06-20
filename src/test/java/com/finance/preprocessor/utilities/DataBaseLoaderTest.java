package com.finance.preprocessor.utilities;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.PairDataRequest;
import com.finance.domain.dto.currencypair.PointTimeFrame;
import com.finance.service.database.CurrencyPairDataPointService;
import com.finance.service.database.CurrencyPairService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataBaseLoaderTest {

    @Autowired
    private DataBaseLoader dataBaseLoader;

    @Autowired
    private CurrencyPairService currencyPairService;

    @Autowired
    private CurrencyPairDataPointService currencyPairDataPointService;

    @Test
    public void loadingTest(){
        LocalDateTime localDateTime1 = LocalDateTime.of(2020,1,1,12,0);
        LocalDateTime localDateTime2 = LocalDateTime.of(2020,1,1,13,0);
        LocalDateTime localDateTime3 = LocalDateTime.of(2020,1,1,14,0);


        String randomName = generateRandomString();

        List<DataPoint> dataPoints = new ArrayList<>(
                Arrays.asList(
                        new DataPoint(localDateTime1, 1.0),
                        new DataPoint(localDateTime2, 1.5),
                        new DataPoint(localDateTime3, 2.0)
                )
        );

        CurrencyPair currencyPair = new CurrencyPair(randomName);

        List<CurrencyPairDataPoint> currencyPairDataPointExpected = new ArrayList<>(
                Arrays.asList(
                        new CurrencyPairDataPoint(localDateTime1, 1.0, currencyPair),
                        new CurrencyPairDataPoint(localDateTime2, 1.5, currencyPair),
                        new CurrencyPairDataPoint(localDateTime3, 2.0, currencyPair)
                )
        );

        CurrencyPairDataPack currencyPairDataPack = new CurrencyPairDataPack(randomName, ChronoUnit.HOURS, dataPoints);

        dataBaseLoader.load(new ArrayList<>(Arrays.asList(currencyPairDataPack)));

        CurrencyPair receivedCurrencyPair = currencyPairService.getCurrencyPair(randomName);

        System.out.println("info " + receivedCurrencyPair);

        Assert.assertNotNull(receivedCurrencyPair);

        PairDataRequest pairDataRequest = new PairDataRequest(randomName,3, PointTimeFrame.H1,0);

        List<CurrencyPairDataPoint> currencyPairHistory = currencyPairDataPointService.getCurrencyPairHistory(pairDataRequest);

        Assert.assertEquals(currencyPairDataPointExpected.stream().map(point -> point.toString()).collect(Collectors.joining()),
                currencyPairHistory.stream().map(point -> point.toString()).collect(Collectors.joining()));
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

    private boolean isPairExist(String name){
        CurrencyPair receivedCurrencyPair = currencyPairService.getCurrencyPair(name);
        return receivedCurrencyPair != null;
    }
}