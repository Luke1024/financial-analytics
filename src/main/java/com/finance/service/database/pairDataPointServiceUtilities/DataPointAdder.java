package com.finance.service.database.pairDataPointServiceUtilities;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataPointAdder {

    @Autowired
    private CurrencyPairHistoryPointRepository repository;

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    private boolean overwrite;

    public void addPoint(List<CurrencyPairDataPoint> currencyPairDataPoints, String currencyPairName, boolean overwrite){
        if(currencyPairDataPoints == null) return;
        if(currencyPairName == null) return;

        this.overwrite = overwrite;

        for(CurrencyPairDataPoint point : currencyPairDataPoints){
            saveDataPoint(point, currencyPairName);
        }
    }

    private void saveDataPoint(CurrencyPairDataPoint point, String currencyPairName){
        if(checkCurrencyPairDataPoint(point)) {
            Optional<CurrencyPair> optionalPair = getCurrencyPair(currencyPairName);
            if (optionalPair.isPresent()) {
                addPointToAlreadyExistingCurrency(point, optionalPair.get());
            }
        }
    }

    private boolean checkCurrencyPairDataPoint(CurrencyPairDataPoint point){
        if(point.getTimeStamp() == null) return false;
        else return true;
    }

    private Optional<CurrencyPair> getCurrencyPair(String currencyPairName) {
        Optional<CurrencyPair> currencyPair = currencyPairRepository.findByCurrencyName(currencyPairName);
        return currencyPair;
    }

    private String addPointToAlreadyExistingCurrency(CurrencyPairDataPoint point, CurrencyPair currencyPair){
        Optional<CurrencyPairDataPoint> currencyPairDataPoint = getDataPointByTimeStamp(point, currencyPair);
        if(currencyPairDataPoint.isPresent()){
            if(this.overwrite){
                overwriteDataPoint(currencyPairDataPoint.get(), point);
                return "";
            } else {
                return "CurrencyPairDataPoint with the same timestamp exist and there is problem with overwriting.";
            }
        } else {
            addDataPointToCurrencyPair(point, currencyPair);
            return "";
        }
    }

    private Optional<CurrencyPairDataPoint> getDataPointByTimeStamp(CurrencyPairDataPoint point, CurrencyPair currencyPair){
        return repository.findPointByDate(point.getTimeStamp(), currencyPair.getId());
    }

    private void overwriteDataPoint(CurrencyPairDataPoint oldPoint, CurrencyPairDataPoint newPoint){
        oldPoint.setValue(newPoint.getValue());
        repository.save(oldPoint);
    }

    private void addDataPointToCurrencyPair(CurrencyPairDataPoint point, CurrencyPair currencyPair){
        currencyPair.addDataPoint(point);
        point.setCurrencyPair(currencyPair);
        currencyPairRepository.save(currencyPair);
    }
}
