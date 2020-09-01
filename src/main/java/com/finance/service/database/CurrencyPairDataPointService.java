package com.finance.service.database;

import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.PairDataRequest;
import com.finance.repository.CurrencyPairRepository;
import com.finance.service.database.pairdatapointserviceutilities.CurrencyPairDataPointCache;
import com.finance.service.database.pairdatapointserviceutilities.DataPointAdder;
import com.finance.service.database.pairdatapointserviceutilities.PairHistoryRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyPairDataPointService {

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    @Autowired
    private PairHistoryRetriever pairHistoryRetriever;

    @Autowired
    private DataPointAdder dataPointAdder;

    @Autowired
    private CurrencyPairDataPointCache cache;

    public CurrencyPairDataPointService() {
        if( ! cache.isLoaded()) cache.load();
    }

    public List<CurrencyPairDataPoint> getCurrencyPairHistory(PairDataRequest pairDataRequest){
        return pairHistoryRetriever.getCurrencyPairHistory(pairDataRequest);
    }

    public void addDataPoints(List<CurrencyPairDataPoint> currencyPairDataPoints, String currencyPairName){
        dataPointAdder.addPoints(currencyPairDataPoints, currencyPairName, false);
    }

    public void addDataPoints(List<CurrencyPairDataPoint> currencyPairDataPoints, String currencyPairName, boolean overwrite){
        dataPointAdder.addPoints(currencyPairDataPoints, currencyPairName, overwrite);
    }
}