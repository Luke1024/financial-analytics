package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.currencyPair.PairDataRequestDto;
import com.finance.domain.dto.currencyPair.PointTimeFrame;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CurrencyPairDataPointServiceTest {

    @Autowired
    private CurrencyPairDataPointService currencyPairDataPointService;

    @Autowired
    private CurrencyPairHistoryPointRepository currencyPairHistoryPointRepository;

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    private LocalDateTime localDateTime1 = LocalDateTime.of(2020,1,1,12,0);
    private LocalDateTime localDateTime2 = LocalDateTime.of(2020,1,1,13,0);
    private LocalDateTime localDateTime3 = LocalDateTime.of(2020,1,1,14,0);
    private LocalDateTime localDateTime4 = LocalDateTime.of(2020,1,1,15,0);
    private LocalDateTime localDateTime5 = LocalDateTime.of(2020,1,1,16,0);

    private CurrencyPair currencyPair = new CurrencyPair("EUR/USD");

    private CurrencyPairDataPoint currencyPairDataPoint1 = new CurrencyPairDataPoint(localDateTime1, 2.0, currencyPair);
    private CurrencyPairDataPoint currencyPairDataPoint2 = new CurrencyPairDataPoint(localDateTime2, 2.5, currencyPair);
    private CurrencyPairDataPoint currencyPairDataPoint3 = new CurrencyPairDataPoint(localDateTime3, 3.0, currencyPair);
    private CurrencyPairDataPoint currencyPairDataPoint4 = new CurrencyPairDataPoint(localDateTime4, 3.5, currencyPair);
    private CurrencyPairDataPoint currencyPairDataPoint5 = new CurrencyPairDataPoint(localDateTime5, 4.0, currencyPair);

    private List<CurrencyPairDataPoint> currencyPairDataPoints = new ArrayList<>(Arrays.asList(
            currencyPairDataPoint1, currencyPairDataPoint2, currencyPairDataPoint3,
            currencyPairDataPoint4, currencyPairDataPoint5));

    @Test
    public void getCurrencyPairHistoryFromLast(){
        currencyPairDataPointService.addHistoryPoints(Arrays.asList(
                currencyPairDataPoint1,
                currencyPairDataPoint2,
                currencyPairDataPoint3,
                currencyPairDataPoint4,
                currencyPairDataPoint5));

        PairDataRequestDto pairDataRequestDto = new PairDataRequestDto("EUR/USD", 3, PointTimeFrame.H1);

        List<CurrencyPairDataPoint> currencyPairDataPoints = currencyPairDataPointService.getCurrencyPairHistory(pairDataRequestDto);

        List<CurrencyPairDataPoint> expectedPoints = Arrays.asList(
                currencyPairDataPoint3,
                currencyPairDataPoint4,
                currencyPairDataPoint5
        );

        Assert.assertEquals(expectedPoints,currencyPairDataPoints);

        currencyPairRepository.deleteById(currencyPairDataPoint1.getPointId());
        currencyPairRepository.deleteById(currencyPairDataPoint2.getPointId());
        currencyPairRepository.deleteById(currencyPairDataPoint3.getPointId());
        currencyPairRepository.deleteById(currencyPairDataPoint4.getPointId());
        currencyPairRepository.deleteById(currencyPairDataPoint5.getPointId());
    }

    @Test
    public void testAddHistoryPoint() {
        /*
        CurrencyPair currencyPair = new CurrencyPair(42L,"EUR/USD");
        LocalDateTime localDateTime1 = LocalDateTime.of(2020,1,1,12,0);
        CurrencyPairDataPoint currencyPairDataPoint1 = new CurrencyPairDataPoint(localDateTime1, 2.0, currencyPair);

        when(currencyPairRepository.findByCurrencyName("EUR/USD"))
                .thenReturn(java.util.Optional.ofNullable(currencyPair));

        when(currencyPairHistoryPointRepository.findPointByDate(localDateTime1,42))
                .thenReturn(java.util.Optional.of(currencyPairDataPoint1));

        List<CurrencyPairDataPoint> inputPoints = new ArrayList<>(Arrays.asList(currencyPairDataPoint1, currencyPairDataPoint1));

        currencyPairDataPointService.addHistoryPoints(inputPoints);

        currencyPairRepository.save(currencyPair);

        for (CurrencyPairDataPoint point : currencyPairDataPoints) {
            currencyPairHistoryPointRepository.save(point);
        }
/*
        PairHistoryRequestDto pairHistoryRequestDto = new PairHistoryRequestDto(
                "EUR/USD", true,
        );

        currencyPairDataPointService.getCurrencyPairHistory();
    }

 */
    }
}