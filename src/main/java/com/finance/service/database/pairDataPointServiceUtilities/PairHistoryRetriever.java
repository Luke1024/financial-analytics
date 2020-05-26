package com.finance.service.database.pairDataPointServiceUtilities;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.PairDataRequest;
import com.finance.domain.dto.currencyPair.PointTimeFrame;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import com.finance.service.database.communicationObjects.DatabaseEntity;
import com.finance.service.database.communicationObjects.DatabaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PairHistoryRetriever {

    private static final ChronoUnit defaultChronoUnitUsedForTimeStampCalculation = ChronoUnit.HOURS;
    private static final int maxPointsRetrieved = 300;

    @Autowired
    private CurrencyPairHistoryPointRepository repository;

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    public DatabaseResponse getCurrencyPairHistory(PairDataRequest pairDataRequest){
        String log = requestDtoFullCheck(pairDataRequest);
        if(log.contains("OK")){
            return realizeRequest(pairDataRequest);
        } else {
            return new DatabaseResponse(null, log, false);
        }
    }

    private String requestDtoFullCheck(PairDataRequest pairDataRequest){
        String log = "";
        if(pairDataRequest.getCurrencyName() == null) log += "CurrencyName is null.";
        if(pairDataRequest.getNumberOfDataPoints() == 0) log += "Number of data points requested is 0.";
        if(pairDataRequest.getPointTimeFrame() == null) log += "PointTimeFrame is null.";
        if(pairDataRequest.isFromLastPoint() == false){
            if(pairDataRequest.getAdoptedlastPoint() == null) log += "LocalDateTime adoptedLastPoint is null.";
        }
        if(log.length() > 0) return log;
        else return "OK";
    }

    private DatabaseResponse realizeRequest(PairDataRequest pairDataRequest){
        Optional<CurrencyPair> currencyPair = getCurrencyPair(pairDataRequest);

        Optional<CurrencyPairDataPoint> lastDataPoint;

        if(currencyPair.isPresent()){
            return processWithPointSearching(currencyPair.get(), pairDataRequest);
        } else {
            return new DatabaseResponse(null, "CurrencyPair not found.", false);
        }
    }

    private DatabaseResponse processWithPointSearching(CurrencyPair currencyPair, PairDataRequest pairDataRequest){
        Optional<CurrencyPairDataPoint> lastDataPoint = getLastDataPoint(currencyPair, pairDataRequest);

        if(lastDataPoint.isPresent()){
            List<CurrencyPairDataPoint> currencyPairDataPoints =
                    getDataPoints(lastDataPoint.get(), pairDataRequest, currencyPair.getId());
            List<DatabaseEntity> databaseEntities = currencyPairDataPoints.stream()
                    .map(point -> (DatabaseEntity) point).collect(Collectors.toList());

            return new DatabaseResponse(databaseEntities, "", true);
        }
        return new DatabaseResponse(null, "Last dataPoint not found", false);
    }

    private Optional<CurrencyPair> getCurrencyPair(PairDataRequest pairDataRequest){
        return currencyPairRepository.findByCurrencyName(pairDataRequest.getCurrencyName());
    }

    private Optional<CurrencyPairDataPoint> getLastDataPoint(CurrencyPair pair, PairDataRequest pairDataRequest){
        long pairId = pair.getId();
        if(pairDataRequest.isFromLastPoint()){
            return repository.getLastDataPoint(pairId);
        } else {
            return repository.findPointByDate(pairDataRequest.getAdoptedlastPoint(), pairId);
        }
    }

    private List<CurrencyPairDataPoint> getDataPoints(CurrencyPairDataPoint lastDataPoint,
                                                      PairDataRequest pairDataRequest,
                                                      long currencyPairId){
        List<CurrencyPairDataPoint> points = new ArrayList<>();

        int dataPointSize = limitToLargeRequest(pairDataRequest.getNumberOfDataPoints());
        PointTimeFrame timeFrame = pairDataRequest.getPointTimeFrame();

        //add point backward
        points.add(lastDataPoint);
        LocalDateTime lastTimeStamp = lastDataPoint.getTimeStamp();
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
        if(timeFrame == PointTimeFrame.H5) return lastTimeStamp.minus(stepBackward * 5, ChronoUnit.HOURS);
        if(timeFrame == PointTimeFrame.D1) return lastTimeStamp.minus(stepBackward, ChronoUnit.DAYS);
        if(timeFrame == PointTimeFrame.W1) return lastTimeStamp.minus(stepBackward, ChronoUnit.WEEKS);
        if(timeFrame == PointTimeFrame.M1) return lastTimeStamp.minus(stepBackward, ChronoUnit.MONTHS);
        return lastTimeStamp.minus(stepBackward, ChronoUnit.HOURS);
    }

    private int limitToLargeRequest(int numberOfDataPoints){
        if(numberOfDataPoints > maxPointsRetrieved) return maxPointsRetrieved;
        return numberOfDataPoints;
    }
}
