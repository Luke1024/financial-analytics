package com.finance.service.database;

import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.currencyPair.PairDataRequestDto;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import com.finance.service.database.pairDataPointServiceUtilities.DataPointAdder;
import com.finance.service.database.pairDataPointServiceUtilities.PairHistoryRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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

    public List<CurrencyPairDataPoint> getCurrencyPairHistory(PairDataRequestDto pairDataRequestDto){
        return pairHistoryRetriever.getCurrencyPairHistory(pairDataRequestDto);
    }

    public Optional<CurrencyPairDataPoint> getPairLastDataPoint(long pair_id){
        return repository.getLastDataPoint(pair_id);
    }

    public void addDataPoints(List<CurrencyPairDataPoint> currencyPairDataPoints){
        dataPointAdder.addPoint(currencyPairDataPoints);
    }
}