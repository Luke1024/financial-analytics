package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.currencyPair.PairDataRequestDto;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
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

    public List<CurrencyPairDataPoint> getCurrencyPairHistory(PairDataRequestDto pairDataRequestDto){
        return pairHistoryRetriever.getCurrencyPairHistory(pairDataRequestDto);
    }

    public void addHistoryPoints(List<CurrencyPairDataPoint> currencyPairDataPoints){
        for(CurrencyPairDataPoint point : currencyPairDataPoints){
            CurrencyPair currencyPair = retrieveCurrency(point);
            repository.save(new CurrencyPairDataPoint(
                    point.getTimeStamp(), point.getValue(),currencyPair));
        }
    }

    private CurrencyPair retrieveCurrency(CurrencyPairDataPoint point) {
        String currencyPairName = point.getCurrencyPair().getCurrencyPairName();
        Optional<CurrencyPair> retrievedCurrency = currencyPairRepository.findByCurrencyName(currencyPairName);
        if (retrievedCurrency.isPresent()) {
            return retrievedCurrency.get();
        } else {
            currencyPairRepository.save(new CurrencyPair(currencyPairName));
            return retrieveCurrency(point);
        }
    }
}