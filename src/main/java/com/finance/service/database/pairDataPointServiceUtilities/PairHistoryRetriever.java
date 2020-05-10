package com.finance.service.database.pairDataPointServiceUtilities;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.currencyPair.PairDataRequestDto;
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

    public DatabaseResponse getCurrencyPairHistory(PairDataRequestDto pairDataRequestDto){
        String log = requestDtoFullCheck(pairDataRequestDto);
        if(log.contains("OK")){
            return realizeRequest(pairDataRequestDto);
        } else {
            return new DatabaseResponse(null, log, false);
        }
    }

    private String requestDtoFullCheck(PairDataRequestDto pairDataRequestDto){
        String log = "";
        if(pairDataRequestDto.getCurrencyName() == null) log += "CurrencyName is null.";
        if(pairDataRequestDto.getNumberOfDataPoints() == 0) log += "Number of data points requested is 0.";
        if(pairDataRequestDto.getPointTimeFrame() == null) log += "PointTimeFrame is null.";
        if(pairDataRequestDto.isFromLastPoint() == false){
            if(pairDataRequestDto.getAdoptedlastPoint() == null) log += "LocalDateTime adoptedLastPoint is null.";
        }
        if(log.length() > 0) return log;
        else return "OK";
    }

    public DatabaseResponse realizeRequest(PairDataRequestDto pairDataRequestDto){
        long currencyPairId = getCurrencyPairId(pairDataRequestDto);

        Optional<CurrencyPairDataPoint> lastDataPoint;

        if(currencyPairId == -1){
            return new DatabaseResponse(null, "CurrencyPair not found.", false);
        } else {
            lastDataPoint = getLastDataPoint(currencyPairId, pairDataRequestDto);
        }

        if(lastDataPoint.isPresent()){
            List<CurrencyPairDataPoint> currencyPairDataPoints =
                    getDataPoints(lastDataPoint.get(), pairDataRequestDto, currencyPairId);
            List<DatabaseEntity> databaseEntities = currencyPairDataPoints.stream()
                    .map(point -> (DatabaseEntity) point).collect(Collectors.toList());

            return new DatabaseResponse(databaseEntities, "", true);
        }
        return new DatabaseResponse(null, "Last dataPoint not found", false);
    }

    private long getCurrencyPairId(PairDataRequestDto pairDataRequestDto){
        if(pairDataRequestDto != null) {
            String currencyName = pairDataRequestDto.getCurrencyName();
            Optional<CurrencyPair> currencyPair = currencyPairRepository.findByCurrencyName(currencyName);
            if (currencyPair.isPresent()) {
                return currencyPair.get().getId();
            }
        }
        return -1;
    }

    private Optional<CurrencyPairDataPoint> getLastDataPoint(long currencyPairId, PairDataRequestDto pairDataRequestDto){
        if(pairDataRequestDto.isFromLastPoint()){
            return repository.getLastDataPoint(currencyPairId);
        } else {
            return repository.findPointByDate(pairDataRequestDto.getAdoptedlastPoint(), currencyPairId);
        }
    }

    private List<CurrencyPairDataPoint> getDataPoints(CurrencyPairDataPoint lastDataPoint,
                                                      PairDataRequestDto pairDataRequestDto,
                                                      long currencyPairId){
        List<CurrencyPairDataPoint> points = new ArrayList<>();

        int dataPointSize = limitToLargeRequest(pairDataRequestDto.getNumberOfDataPoints());
        PointTimeFrame timeFrame = pairDataRequestDto.getPointTimeFrame();

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
