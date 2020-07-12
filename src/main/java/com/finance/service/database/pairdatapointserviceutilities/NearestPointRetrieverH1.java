package com.finance.service.database.pairdatapointserviceutilities;

import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.currencypair.PointTimeFrame;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NearestPointRetrieverH1 {

    @Autowired
    private CurrencyPairHistoryPointRepository repository;

    public CurrencyPairDataPoint findNearestPoint(LocalDateTime timeStamp, long currencyPairId, PointTimeFrame timeFrame) {
        if(timeStamp == null || timeFrame == null) return null;
        int searchDistance = loadSearchDistanceH1(timeFrame);
        if(searchDistance == 0) return null;

        LocalDateTime start = timeStamp.minusHours(searchDistance);
        LocalDateTime stop = timeStamp.plusHours(searchDistance);

        List<CurrencyPairDataPoint> receivedDataPoints = repository.retrieveByTimeRangeAndCurrencyName(currencyPairId, start, stop);

        if(receivedDataPoints.isEmpty()) return null;

        return getNearestPoint(receivedDataPoints, timeStamp);
    }

    private CurrencyPairDataPoint getNearestPoint(List<CurrencyPairDataPoint> points, LocalDateTime timeStamp){
        List<Integer> chronoDistances = new ArrayList<>();
        for(CurrencyPairDataPoint point : points){
            chronoDistances.add(computeH1Distance(point, timeStamp));
        }
        if(chronoDistances.isEmpty()) return null;
        int minIndex = chronoDistances.indexOf(Collections.min(chronoDistances));

        return points.get(minIndex);
    }

    private Integer computeH1Distance(CurrencyPairDataPoint pairDataPoint, LocalDateTime centerPoint){
        if(pairDataPoint == null) return null;
        if(pairDataPoint.getTimeStamp() == null) return null;
        Duration duration = Duration.between(pairDataPoint.getTimeStamp(), centerPoint);
        return (int) Math.abs(duration.toHours());
    }

    private int loadSearchDistanceH1(PointTimeFrame timeFrame) {
        if (timeFrame == PointTimeFrame.H1) return 0;
        if (timeFrame == PointTimeFrame.H5) return 2;
        if (timeFrame == PointTimeFrame.D1) return 8;
        if (timeFrame == PointTimeFrame.M1) return 20;
        return 0;
    }
}
