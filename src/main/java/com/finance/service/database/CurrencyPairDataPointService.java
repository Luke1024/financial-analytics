package com.finance.service.database;

import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.PairDataRequest;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import com.finance.service.database.pairDataPointServiceUtilities.DataPointAdder;
import com.finance.service.database.pairDataPointServiceUtilities.PairHistoryRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CurrencyPairDataPointService {

    private static final ChronoUnit defaultChronoUnitUsedForTimeStampCalculation = ChronoUnit.HOURS;

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