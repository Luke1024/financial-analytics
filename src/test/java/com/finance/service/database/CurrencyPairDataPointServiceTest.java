package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.currencyPair.PairDataRequestDto;
import com.finance.domain.dto.currencyPair.PointTimeFrame;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import com.finance.service.database.communicationObjects.DatabaseResponse;
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
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
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
    public void getCurrencyPairHistory_CurrencyNameNull(){
        PairDataRequestDto pairDataRequestDto = new PairDataRequestDto(null, 5,PointTimeFrame.D1, LocalDateTime.now());

        DatabaseResponse response = currencyPairDataPointService.getCurrencyPairHistory(pairDataRequestDto);

        Assert.assertEquals(null, response.getRequestedObjects());
        Assert.assertEquals("CurrencyName is null.", response.getLog());
        Assert.assertEquals(false, response.isOK());
    }

    @Test
    public void getCurrencyPairHistory_requestedNumberOfDataPointsIsZero(){
        PairDataRequestDto pairDataRequestDto = new PairDataRequestDto("sdasd", 0, PointTimeFrame.D1, LocalDateTime.now());

        DatabaseResponse response = currencyPairDataPointService.getCurrencyPairHistory(pairDataRequestDto);

        Assert.assertEquals(null, response.getRequestedObjects());
        Assert.assertEquals("Number of data points requested is 0.", response.getLog());
        Assert.assertEquals(false, response.isOK());
    }

    @Test
    public void getCurrencyPairHistory_requestedNumberOfDataPointsIsLargerThanZero(){
        String randomName = generateRandomString();
        if(isPairExist(randomName)){
            getCurrencyPairHistory_requestedNumberOfDataPointsIsLargerThanZero();
        }

        PairDataRequestDto pairDataRequestDto = new PairDataRequestDto(randomName,1,PointTimeFrame.D1, LocalDateTime.now());

        DatabaseResponse response = currencyPairDataPointService.getCurrencyPairHistory(pairDataRequestDto);

        Assert.assertEquals(null, response.getRequestedObjects());
        Assert.assertEquals("CurrencyPair not found.", response.getLog());
        Assert.assertEquals(false, response.isOK());
    }

    @Test
    public void getCurrencyPairHistory_PointTimeFrameIsNull(){
        PairDataRequestDto pairDataRequestDto = new PairDataRequestDto("asfadfaf", 2, null, LocalDateTime.now());

        DatabaseResponse response = currencyPairDataPointService.getCurrencyPairHistory(pairDataRequestDto);

        Assert.assertEquals(null, response.getRequestedObjects());
        Assert.assertEquals("PointTimeFrame is null.", response.getLog());
        Assert.assertEquals(false, response.isOK());
    }

    @Test
    public void getCurrencyPairHistory_fromLastPointFalse_adoptedLastPointIsNull(){
        PairDataRequestDto pairDataRequestDto = new PairDataRequestDto("asdasdad", 3, PointTimeFrame.D1,null);

        DatabaseResponse response = currencyPairDataPointService.getCurrencyPairHistory(pairDataRequestDto);

        Assert.assertEquals(null, response.getRequestedObjects());
        Assert.assertEquals("LocalDateTime adoptedLastPoint is null.", response.getLog());
        Assert.assertEquals(false, response.isOK());
    }

    @Test
    public void getCurrencyPairHistory_CurrencyPairNotExists(){
        String randomName = generateRandomString();
        if(isPairExist(randomName)){
            getCurrencyPairHistory_CurrencyPairNotExists();
        }

        PairDataRequestDto pairDataRequestDto = new PairDataRequestDto(randomName, 3, PointTimeFrame.D1);

        DatabaseResponse response = currencyPairDataPointService.getCurrencyPairHistory(pairDataRequestDto);

        Assert.assertEquals(null, response.getRequestedObjects());
        Assert.assertEquals("CurrencyPair not found.", response.getLog());
        Assert.assertEquals(false, response.isOK());
    }

    @Test
    public void getCurrencyPairHistory_Adding_Deleting(){
        String randomName = generateRandomString();
        if(isPairExist(randomName)){
            getCurrencyPairHistory_CurrencyPairNotExists();
        }

        CurrencyPair newPair = new CurrencyPair(randomName);

        DatabaseResponse savingResponse = currencyPairService.saveCurrencyPair(newPair, false);

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

        DatabaseResponse savingPointResponse = currencyPairDataPointService.addDataPoints(dataPointsToSave, randomName);

        Assert.assertEquals("", savingPointResponse.getLog());
        Assert.assertEquals(true, savingPointResponse.isOK());

        PairDataRequestDto pairDataRequestDto = new PairDataRequestDto(randomName, 5, PointTimeFrame.H1);

        DatabaseResponse requestingResponse = currencyPairDataPointService.getCurrencyPairHistory(pairDataRequestDto);
        System.out.println("Requesting response " + requestingResponse.getLog());
        Assert.assertEquals(true, requestingResponse.isOK());

        DatabaseResponse deletingResponse = currencyPairService.deleteById(newPair.getId());
        Assert.assertEquals(true, deletingResponse.isOK());

        DatabaseResponse checkingIfDeletedResponse = currencyPairService.getCurrencyPair(randomName);
        Assert.assertEquals("CurrencyPair not found.", checkingIfDeletedResponse.getLog());
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


/*
    @Test
    public void getCurrencyPairHistoryFromLast(){
        currencyPairDataPointService.addDataPoints((Arrays.asList(
                currencyPairDataPoint1,
                currencyPairDataPoint2,
                currencyPairDataPoint3,
                currencyPairDataPoint4,
                currencyPairDataPoint5)), true);

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