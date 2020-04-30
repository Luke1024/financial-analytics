package com.finance.database.internals.service.database.pairDataPointServiceUtilities;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.currencyPair.PairDataRequestDto;
import com.finance.domain.dto.currencyPair.PointTimeFrame;
import com.finance.database.internals.repository.CurrencyPairHistoryPointRepository;
import com.finance.database.internals.repository.CurrencyPairRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PairHistoryRetrieverTest {

    @Autowired
    private PairHistoryRetriever pairHistoryRetriever;

    @MockBean
    private CurrencyPairRepository currencyPairRepository;

    @MockBean
    private CurrencyPairHistoryPointRepository currencyPairHistoryPointRepository;

    private CurrencyPair currencyPair = new CurrencyPair(42L, "EUR/USD");

    private LocalDateTime localDateTime1 = LocalDateTime.of(2020, 1, 1, 13, 0);
    private LocalDateTime localDateTime2 = LocalDateTime.of(2020, 1, 1, 14, 0);
    private LocalDateTime localDateTime3 = LocalDateTime.of(2020, 1, 1, 15, 0);
    private LocalDateTime localDateTime4 = LocalDateTime.of(2020, 1, 1, 16, 0);
    private LocalDateTime localDateTime5 = LocalDateTime.of(2020, 1, 1, 17, 0);

    private CurrencyPairDataPoint currencyPairDataPoint1 = new CurrencyPairDataPoint(localDateTime1, 1.0, currencyPair);
    private CurrencyPairDataPoint currencyPairDataPoint2 = new CurrencyPairDataPoint(localDateTime2, 1.5, currencyPair);
    private CurrencyPairDataPoint currencyPairDataPoint3 = new CurrencyPairDataPoint(localDateTime3, 2.0, currencyPair);
    private CurrencyPairDataPoint currencyPairDataPoint4 = new CurrencyPairDataPoint(localDateTime4, 2.5, currencyPair);
    private CurrencyPairDataPoint currencyPairDataPoint5 = new CurrencyPairDataPoint(localDateTime5, 3.0, currencyPair);

    @Test
    public void testGetHistoryFull() {
        when(currencyPairRepository.findByCurrencyName("EUR/USD"))
                .thenReturn(Optional.ofNullable(currencyPair)).thenReturn(Optional.ofNullable(currencyPair));

        when(currencyPairHistoryPointRepository.getLastDataPoint(42))
                .thenReturn(Optional.ofNullable(currencyPairDataPoint5));

        when(currencyPairHistoryPointRepository.findPointByDate(localDateTime4,42))
                .thenReturn(Optional.ofNullable(currencyPairDataPoint4));
        when(currencyPairHistoryPointRepository.findPointByDate(localDateTime3,42))
                .thenReturn(Optional.ofNullable(currencyPairDataPoint3));
        when(currencyPairHistoryPointRepository.findPointByDate(localDateTime2,42))
                .thenReturn(Optional.ofNullable(currencyPairDataPoint2));
        when(currencyPairHistoryPointRepository.findPointByDate(localDateTime1,42))
                .thenReturn(Optional.ofNullable(currencyPairDataPoint1));


        PairDataRequestDto pairDataRequestDto = new PairDataRequestDto(
                "EUR/USD", 3, PointTimeFrame.H1);

        List<CurrencyPairDataPoint> expectedDataPoints = new ArrayList<>(Arrays.asList(
                currencyPairDataPoint3,
                currencyPairDataPoint4,
                currencyPairDataPoint5
        ));

        Assert.assertEquals(expectedDataPoints.stream().map(point -> point.toString()).collect(Collectors.joining()),
                pairHistoryRetriever.getCurrencyPairHistory(pairDataRequestDto).stream().map(point -> point.toString())
        .collect(Collectors.joining()));
    }

    @Test
    public void testGetEarlierHistory() {
        when(currencyPairRepository.findByCurrencyName("EUR/USD"))
                .thenReturn(Optional.ofNullable(currencyPair)).thenReturn(Optional.ofNullable(currencyPair));

        when(currencyPairHistoryPointRepository.getLastDataPoint(42))
                .thenReturn(Optional.ofNullable(currencyPairDataPoint5));

        when(currencyPairHistoryPointRepository.findPointByDate(localDateTime4,42))
                .thenReturn(Optional.ofNullable(currencyPairDataPoint4));
        when(currencyPairHistoryPointRepository.findPointByDate(localDateTime3,42))
                .thenReturn(Optional.ofNullable(currencyPairDataPoint3));
        when(currencyPairHistoryPointRepository.findPointByDate(localDateTime2,42))
                .thenReturn(Optional.ofNullable(currencyPairDataPoint2));
        when(currencyPairHistoryPointRepository.findPointByDate(localDateTime1,42))
                .thenReturn(Optional.ofNullable(currencyPairDataPoint1));


        PairDataRequestDto pairDataRequestDto = new PairDataRequestDto(
                "EUR/USD", 3, PointTimeFrame.H1, localDateTime4);

        List<CurrencyPairDataPoint> expectedDataPoints = new ArrayList<>(Arrays.asList(
                currencyPairDataPoint2,
                currencyPairDataPoint3,
                currencyPairDataPoint4
        ));

        Assert.assertEquals(expectedDataPoints.stream().map(point -> point.toString()).collect(Collectors.joining()),
                pairHistoryRetriever.getCurrencyPairHistory(pairDataRequestDto).stream().map(point -> point.toString())
                        .collect(Collectors.joining()));
    }
}