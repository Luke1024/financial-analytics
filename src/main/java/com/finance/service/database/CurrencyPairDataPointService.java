package com.finance.service.database;

import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.PairDataRequest;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import com.finance.service.database.communicationObjects.DatabaseResponse;
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

    public DatabaseResponse getCurrencyPairHistory(PairDataRequest pairDataRequest){
        return pairHistoryRetriever.getCurrencyPairHistory(pairDataRequest);
    }
/*
    public DatabaseResponse getPairLastDataPoint(long pair_id){
        if(pair_id < 1){
            return new DatabaseResponse(null, "Id cannot be lower than 1.", false);
        } else {
            Optional<CurrencyPairDataPoint> currencyPairDataPoint = repository.getLastDataPoint(pair_id);
            if(currencyPairDataPoint.isPresent()) {
                return new DatabaseResponse(Arrays.asList(currencyPairDataPoint.get()), "", true);
            } else return new DatabaseResponse(null, "Last dataPoint not found", false);
        }
    }
*/
    public DatabaseResponse addDataPoints(List<CurrencyPairDataPoint> currencyPairDataPoints, String currencyPairName){
        return dataPointAdder.addPoint(currencyPairDataPoints, currencyPairName, false);
    }

    public DatabaseResponse addDataPoints(List<CurrencyPairDataPoint> currencyPairDataPoints, String currencyPairName, boolean overwrite){
        return dataPointAdder.addPoint(currencyPairDataPoints, currencyPairName, overwrite);
    }
}