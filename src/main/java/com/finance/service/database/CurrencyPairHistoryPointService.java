package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairHistoryPoint;
import com.finance.domain.dto.PairHistoryRequestDto;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyPairHistoryPointService {
    @Autowired
    private CurrencyPairHistoryPointRepository repository;

    @Autowired
    private CurrencyPairService currencyPairService;

    public List<CurrencyPairHistoryPoint> getCurrencyPairHistory(PairHistoryRequestDto pairHistoryRequestDto){
        return repository.retrieveByTimeRangeAndCurrencyName(
                pairHistoryRequestDto.getStart(), pairHistoryRequestDto.getStop(), pairHistoryRequestDto.getPairName());
    }

    public void addHistoryPoints(List<CurrencyPairHistoryPoint> currencyPairHistoryPoints){
        for(CurrencyPairHistoryPoint point : currencyPairHistoryPoints){
            CurrencyPair currencyPair = retrieveCurrency(point);
            repository.save(new CurrencyPairHistoryPoint(
                    point.getTimeStamp(), point.getValue(),currencyPair));
        }
    }

    private CurrencyPair retrieveCurrency(CurrencyPairHistoryPoint point){
        String currencyPairName = point.getCurrencyPair().getCurrencyPairName();
        Optional<CurrencyPair> retrievedCurrency = currencyPairService.getCurrencyPair(currencyPairName);
        if(retrievedCurrency.isPresent()) {
            return retrievedCurrency.get();
        } else {
            currencyPairService.saveCurrencyPair(new CurrencyPair(currencyPairName));
            return retrieveCurrency(point);
        }
    }
}