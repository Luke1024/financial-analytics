package com.finance.service.database.pairDataPointServiceUtilities;

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

@Service
public class PairHistoryRetriever {

    private static final ChronoUnit defaultChronoUnitUsedForTimeStampCalculation = ChronoUnit.HOURS;

    @Autowired
    private CurrencyPairHistoryPointRepository repository;

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    public List<CurrencyPairDataPoint> getCurrencyPairHistory(PairDataRequestDto pairDataRequestDto){
        CurrencyPairDataPoint lastDataPoint = getDatasetEndingPoint(pairDataRequestDto);
        List<CurrencyPairDataPoint> restOfThePoints = retrieveDataset(lastDataPoint, pairDataRequestDto);
    }

    private CurrencyPairDataPoint getDatasetEndingPoint(PairDataRequestDto pairDataRequestDto){
        String currencyName = pairDataRequestDto.getCurrencyName();
        LocalDateTime lastPointTimeStamp = pairDataRequestDto.getLastPoint();

        if(pairDataRequestDto.isFromLastPoint()){
            return repository.getLastDataPoint(currencyName);
        } else {
            return repository.findPointByDate(currencyName, lastPointTimeStamp);
        }
    }

    private List<CurrencyPairDataPoint> retrieveDataset(CurrencyPairDataPoint lastDataPoint,
                                                        PairDataRequestDto pairDataRequestDto){
        List<CurrencyPairDataPoint> points = new ArrayList<>();
        int dataPointSize = pairDataRequestDto.getNumberOfDataPoints();
        PointTimeFrame timeFrame = pairDataRequestDto.getPointTimeFrame();

        //add point backward
        points.add(lastDataPoint);
        LocalDateTime lastTimeStamp = lastDataPoint.getTimeStamp();
        for(int i=0; i<dataPointSize; i++){
            LocalDateTime searchedTime = calculateTimeBackward(timeFrame ,i+1, lastTimeStamp);
            points.add(repository.findPointByDate(pairDataRequestDto.getCurrencyName(), searchedTime));
        }
        return points;
    }

    private LocalDateTime calculateTimeBackward(PointTimeFrame timeFrame, int stepBackward, LocalDateTime lastTimeStamp){
        if(timeFrame == PointTimeFrame.H1) return lastTimeStamp.minus(stepBackward, ChronoUnit.HOURS);
        if(timeFrame == PointTimeFrame.H5) return lastTimeStamp.minus(stepBackward * 5, ChronoUnit.HOURS);
        if(timeFrame == PointTimeFrame.D1) return lastTimeStamp.minus(stepBackward, ChronoUnit.DAYS);
        if(timeFrame == PointTimeFrame.W1) return lastTimeStamp.minus(stepBackward, ChronoUnit.WEEKS);
        if(timeFrame == PointTimeFrame.M1) return lastTimeStamp.minus(stepBackward, ChronoUnit.MONTHS);
        return lastTimeStamp.minus(stepBackward, ChronoUnit.HOURS);
    }
}
