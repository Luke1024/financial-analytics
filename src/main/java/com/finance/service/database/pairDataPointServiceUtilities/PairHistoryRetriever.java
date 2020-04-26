package com.finance.service.database.pairDataPointServiceUtilities;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.currencyPair.PairDataRequestDto;
import com.finance.domain.dto.currencyPair.PointTimeFrame;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PairHistoryRetriever {

    private static final ChronoUnit defaultChronoUnitUsedForTimeStampCalculation = ChronoUnit.HOURS;
    private static final int maxPointsRetrieved = 300;

    @Autowired
    private CurrencyPairHistoryPointRepository repository;

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    public List<CurrencyPairDataPoint> getCurrencyPairHistory(PairDataRequestDto pairDataRequestDto){
        long currencyPairId = getCurrencyPairId(pairDataRequestDto);
        Optional<CurrencyPairDataPoint> lastDataPoint = null;
        if(currencyPairId != -1) {
            lastDataPoint = getDatasetEndingPoint(currencyPairId, pairDataRequestDto);
        }
        if(lastDataPoint.isPresent() && currencyPairId != -1){
            return retrieveDataset(lastDataPoint.get(), pairDataRequestDto, currencyPairId);
        }
        return new ArrayList<>();
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

    private Optional<CurrencyPairDataPoint> getDatasetEndingPoint(long currencyPairId, PairDataRequestDto pairDataRequestDto){
        LocalDateTime lastPointTimeStamp = pairDataRequestDto.getLastPoint();

        if(pairDataRequestDto.isFromLastPoint()){
            return repository.getLastDataPoint(currencyPairId);
        } else {
            return repository.findPointByDate(lastPointTimeStamp, currencyPairId);
        }
    }

    private List<CurrencyPairDataPoint> retrieveDataset(CurrencyPairDataPoint lastDataPoint,
                                                        PairDataRequestDto pairDataRequestDto,
                                                        long currencyPairId){
        List<CurrencyPairDataPoint> points = new ArrayList<>();

        if(currencyPairId != -1) {
            int dataPointSize = limitToLargeRequest(pairDataRequestDto.getNumberOfDataPoints());
            PointTimeFrame timeFrame = pairDataRequestDto.getPointTimeFrame();

            //add point backward
            points.add(lastDataPoint);
            LocalDateTime lastTimeStamp = lastDataPoint.getTimeStamp();
            for (int i = 0; i < dataPointSize; i++) {
                LocalDateTime searchedTime = calculateTimeBackward(timeFrame, i + 1, lastTimeStamp);
                Optional<CurrencyPairDataPoint> retrievedPoint = repository.findPointByDate(searchedTime, currencyPairId);
                if(retrievedPoint.isPresent()){
                    points.add(retrievedPoint.get());
                }
            }
        }
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
