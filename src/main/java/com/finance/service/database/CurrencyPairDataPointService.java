package com.finance.service.database;

import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.PairDataRequest;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import com.finance.service.database.pairdatapointserviceutilities.DataPointAdder;
import com.finance.service.database.pairdatapointserviceutilities.PairHistoryRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyPairDataPointService {

    @Autowired
    private CurrencyPairHistoryPointRepository repository;

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    @Autowired
    private PairHistoryRetriever pairHistoryRetriever;

    @Autowired
    private DataPointAdder dataPointAdder;

    public List<CurrencyPairDataPoint> getCurrencyPairHistory(PairDataRequest pairDataRequest){
        return pairHistoryRetriever.getCurrencyPairHistory(pairDataRequest);
    }

    public void addDataPoints(List<CurrencyPairDataPoint> currencyPairDataPoints, String currencyPairName){
        dataPointAdder.addPoint(currencyPairDataPoints, currencyPairName, false);
    }

    public void addDataPoints(List<CurrencyPairDataPoint> currencyPairDataPoints, String currencyPairName, boolean overwrite){
        dataPointAdder.addPoint(currencyPairDataPoints, currencyPairName, overwrite);
    }
}