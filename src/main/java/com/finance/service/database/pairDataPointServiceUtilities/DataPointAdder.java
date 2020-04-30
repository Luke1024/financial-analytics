package com.finance.service.database.pairDataPointServiceUtilities;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DataPointAdder {

    @Autowired
    private CurrencyPairHistoryPointRepository repository;

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    public void addPoint(List<CurrencyPairDataPoint> currencyPairDataPoints){
        for(CurrencyPairDataPoint point : currencyPairDataPoints){
            saveDataPoint(point);
        }
    }

    private void saveDataPoint(CurrencyPairDataPoint point) {
        if(point != null) checkIfCurrencyPairExist(point);
    }

    private void checkIfCurrencyPairExist(CurrencyPairDataPoint point) {
        if(point.getCurrencyPair() != null) checkIfCurrencyNameExist(point);
    }

    private void checkIfCurrencyNameExist(CurrencyPairDataPoint point) {
        if(point.getCurrencyPair().getCurrencyPairName() != null) checkIfCurrencyPairExistInDatabase(point);
    }

    private void checkIfCurrencyPairExistInDatabase(CurrencyPairDataPoint point) {
        String currencyPairName = point.getCurrencyPair().getCurrencyPairName();
        Optional<CurrencyPair> currencyPair = currencyPairRepository.findByCurrencyName(currencyPairName);
        if(currencyPair.isPresent()) addPointToCurrencyPair(currencyPair.get(), point);
        else {
            addCurrencyPair(point);
            checkIfCurrencyPairExistInDatabase(point);
        }
    }

    private void addPointToCurrencyPair(CurrencyPair currencyPair, CurrencyPairDataPoint currencyPairDataPoint) {
        repository.save(new CurrencyPairDataPoint(currencyPairDataPoint.getTimeStamp(), currencyPairDataPoint.getValue(),
                currencyPair));
    }

    private void addCurrencyPair(CurrencyPairDataPoint currencyPairDataPoint) {
        currencyPairRepository.save(currencyPairDataPoint.getCurrencyPair());
    }
}