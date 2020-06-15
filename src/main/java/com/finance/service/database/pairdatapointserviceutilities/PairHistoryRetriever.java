package com.finance.service.database.pairdatapointserviceutilities;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.PairDataRequest;
import com.finance.domain.dto.currencypair.PointTimeFrame;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PairHistoryRetriever {

    private static final int maxPointsRetrieved = 1000;

    private Logger logger = Logger.getLogger(PairHistoryRetriever.class.getName());

    @Autowired
    private CurrencyPairHistoryPointRepository repository;

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    public List<CurrencyPairDataPoint> getCurrencyPairHistory(PairDataRequest pairDataRequest){
        if( ! dtoCheck(pairDataRequest)){
            return Collections.emptyList();
        }

        CurrencyPair currencyPair = getCurrencyPair(pairDataRequest);
        if(currencyPair == null) {
            logger.log(Level.WARNING, "CurrencyPair " + pairDataRequest.getCurrencyName() + "not found.");
            return Collections.emptyList();
        }

        CurrencyPairDataPoint lastDataPoint = getLastDataPoint(currencyPair);
        if(lastDataPoint == null) return Collections.emptyList();

        CurrencyPairDataPoint lastStartingPoint = getLastStartingPoint(lastDataPoint.getTimeStamp(), pairDataRequest, currencyPair.getId());
        int dataPointSize = limitTooLargeRequest(pairDataRequest.getNumberOfDataPoints());
        PointTimeFrame timeFrame = pairDataRequest.getPointTimeFrame();
        long currencyPairId = currencyPair.getId();

        return getCurrencyPairDataPoints(lastStartingPoint, dataPointSize, timeFrame, currencyPairId);
    }

    private boolean dtoCheck(PairDataRequest pairDataRequest){
        if(pairDataRequest.getCurrencyName() == null){
            logger.log(Level.WARNING, "CurrencyName in PairDataRequest is null.");
            return false;
        }
        if(pairDataRequest.getNumberOfDataPoints() == 0){
            logger.log(Level.WARNING, "NumberOfDataPoints in PairDataRequest is 0.");
        }
        if(pairDataRequest.getPointTimeFrame() == null){
            logger.log(Level.WARNING, "PointTimeFrame in PairDataRequest is 0.");
        }
        return true;
    }

    private CurrencyPair getCurrencyPair(PairDataRequest pairDataRequest){
        String currencyName = pairDataRequest.getCurrencyName();
        Optional<CurrencyPair> currencyPair = currencyPairRepository.findByCurrencyName(currencyName);
        if(currencyPair.isPresent()){
            return currencyPair.get();
        } else {
            return null;
        }
    }

    private CurrencyPairDataPoint getLastDataPoint(CurrencyPair pair){
        Optional<CurrencyPairDataPoint> currencyPairDataPoint = repository.getLastDataPoint(pair.getId());
        if(currencyPairDataPoint.isPresent()){
            return currencyPairDataPoint.get();
        } else {
            logger.log(Level.WARNING, "Last datapoint in CurrencyPair " + pair.getCurrencyPairName() + " not found.");
            return null;
        }
    }

    private CurrencyPairDataPoint getLastStartingPoint(LocalDateTime lastPointInDataset,
                                                       PairDataRequest pairDataRequest, long currencyPairId){
        PointTimeFrame pointTimeFrame = pairDataRequest.getPointTimeFrame();
        int pointCountBeforeLast = pairDataRequest.getPointsBeforeLast();

        LocalDateTime lastStartingPointDateTime = calculateTimeBackward(pointTimeFrame, pointCountBeforeLast, lastPointInDataset);

        Optional<CurrencyPairDataPoint> pairDataPoint = repository.findPointByDate(lastStartingPointDateTime, currencyPairId);

        if(pairDataPoint.isPresent()){
            return pairDataPoint.get();
        } else {
            return null;
        }
    }

    private int limitTooLargeRequest(int numberOfDataPoints){
        if(numberOfDataPoints > maxPointsRetrieved) return maxPointsRetrieved;
        return numberOfDataPoints;
    }

    private List<CurrencyPairDataPoint> getCurrencyPairDataPoints(CurrencyPairDataPoint lastStartingPoint, int dataPointSize,
                                                                  PointTimeFrame timeFrame, long currencyPairId) {
        List<CurrencyPairDataPoint> points = new ArrayList<>();

        //add point backward
        points.add(lastStartingPoint);
        LocalDateTime lastTimeStamp = lastStartingPoint.getTimeStamp();
        for (int i = 0; i < dataPointSize-1; i++) {
            LocalDateTime searchedTime = calculateTimeBackward(timeFrame, i + 1, lastTimeStamp);
            Optional<CurrencyPairDataPoint> retrievedPoint = repository.findPointByDate(searchedTime, currencyPairId);
            if (retrievedPoint.isPresent()) {
                points.add(retrievedPoint.get());
            }
        }
        Collections.reverse(points);
        return points;
    }

    private LocalDateTime calculateTimeBackward(PointTimeFrame timeFrame, int stepBackward,
                                                LocalDateTime lastTimeStamp){
        if(timeFrame == PointTimeFrame.H1) return lastTimeStamp.minus(stepBackward, ChronoUnit.HOURS);
        if(timeFrame == PointTimeFrame.H5) return lastTimeStamp.minus((long) stepBackward * 5, ChronoUnit.HOURS);
        if(timeFrame == PointTimeFrame.D1) return lastTimeStamp.minus(stepBackward, ChronoUnit.DAYS);
        if(timeFrame == PointTimeFrame.W1) return lastTimeStamp.minus(stepBackward, ChronoUnit.WEEKS);
        if(timeFrame == PointTimeFrame.M1) return lastTimeStamp.minus(stepBackward, ChronoUnit.MONTHS);
        return lastTimeStamp.minus(stepBackward, ChronoUnit.HOURS);
    }
}
