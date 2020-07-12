package com.finance.service.database.pairdatapointserviceutilities;

import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.currencypair.PointTimeFrame;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class NearestPointRetriever {

    @Autowired
    private CurrencyPairHistoryPointRepository repository;

    public CurrencyPairDataPoint findNearestPoint(LocalDateTime timeStamp, long currencyPairId, PointTimeFrame timeFrame) {
       // int searchDistance = loadSearchDistanceH1(timeFrame);
        return null;
    }
/*
    private int loadSearchDistanceH1(PointTimeFrame timeFrame) {
        if (timeFrame == PointTimeFrame.H1) return 0;
        if (timeFrame == PointTimeFrame.H5) return 2;
        if (timeFrame == PointTimeFrame.D1) return 8;
        if (timeFrame == PointTimeFrame.M1) return 20;
    }
    */
}
