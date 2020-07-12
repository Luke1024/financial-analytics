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

    private Logger logger = Logger.getLogger(PairHistoryRetriever.class.getName());

    private NearestPointRetriever nearestPointRetriever = new NearestPointRetriever();

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

        LocalDateTime lastDateTime = getLastDataPointDateTime(currencyPair.getId());

        int dataPointSize = pairDataRequest.getNumberOfDataPoints();
        PointTimeFrame timeFrame = pairDataRequest.getPointTimeFrame();
        long currencyPairId = currencyPair.getId();

        return getCurrencyPairDataPoints(lastDateTime, dataPointSize, timeFrame, currencyPairId);
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

    private LocalDateTime getLastDataPointDateTime(long currencyPairId){
        Optional<CurrencyPairDataPoint> currencyPairDataPoint = repository.getLastDataPoint(currencyPairId);
        if(currencyPairDataPoint.isPresent()){
            return currencyPairDataPoint.get().getTimeStamp();
        } else {
            return null;
        }
    }

    private List<CurrencyPairDataPoint> getCurrencyPairDataPoints(LocalDateTime lastDateTime, int dataPointSize,
                                                                  PointTimeFrame timeFrame, long currencyPairId) {
        List<CurrencyPairDataPoint> points = new ArrayList<>();

        List<LocalDateTime> dates = calculatePointDates(lastDateTime, dataPointSize, timeFrame);

        List<CurrencyPairDataPoint> dataPoints = retrieveDataPoints(dates, timeFrame, currencyPairId);

        return dataPoints;
    }

    private List<CurrencyPairDataPoint> retrieveDataPoints(List<LocalDateTime> dates,
                                                           PointTimeFrame timeFrame, long currencyPairId){
        List<CurrencyPairDataPoint> points = new ArrayList<>();

        for (int i = 0; i < dates.size(); i++) {
            CurrencyPairDataPoint retrievedPoint = getDataPoint(dates.get(i), currencyPairId, timeFrame);
            if (retrievedPoint != null) {
                points.add(retrievedPoint);
            } else {
                points.add(new CurrencyPairDataPoint(dates.get(i), 0.0));
            }
        }
        Collections.reverse(points);
        return points;
    }

    private CurrencyPairDataPoint getDataPoint(LocalDateTime timeStamp, long currencyPairId, PointTimeFrame timeFrame){
        Optional<CurrencyPairDataPoint> retrievedPoint = repository.findPointByDate(timeStamp, currencyPairId);
        if(retrievedPoint.isPresent()){
            return retrievedPoint.get();
        } else {
            return nearestPointRetriever.findNearestPoint(timeStamp, currencyPairId, timeFrame);
        }
    }

    private List<LocalDateTime> calculatePointDates(LocalDateTime lastDateTime, int dataPointSize,
                                                    PointTimeFrame timeFrame){
        List<LocalDateTime> dates = new ArrayList<>();
        for(int i=0; i<dataPointSize; i++){
            dates.add(calculateTimeBackward(timeFrame, i, lastDateTime));
        }
        return dates;
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
