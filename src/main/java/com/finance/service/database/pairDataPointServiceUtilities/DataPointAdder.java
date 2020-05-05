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

    public void addPoint(List<CurrencyPairDataPoint> currencyPairDataPoints, boolean overwrite){
        this.overwrite = overwrite;
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
        if(currencyPair.isPresent()) checkIfPointWithTheSameTimeStampAlreadyExistInDatabase(currencyPair.get(), point);
        else {
            addCurrencyPair(point);
            checkIfCurrencyPairExistInDatabase(point);
        }
    }

    private void checkIfPointWithTheSameTimeStampAlreadyExistInDatabase(CurrencyPair currencyPair, CurrencyPairDataPoint point){
        Optional<CurrencyPairDataPoint> dataPoint = repository.findPointByDate(point.getTimeStamp(), currencyPair.getId());
        if(dataPoint.isPresent()){
            if(this.overwrite == true) {
                deleteDataPoint(dataPoint.get());
                addPointToCurrencyPair(currencyPair, point);
            }
        }
    }

    private void deleteDataPoint(CurrencyPairDataPoint dataPoint){
        repository.deleteById(dataPoint.getPointId());
    }

    private void addPointToCurrencyPair(CurrencyPair currencyPair, CurrencyPairDataPoint currencyPairDataPoint) {
        repository.save(new CurrencyPairDataPoint(currencyPairDataPoint.getTimeStamp(), currencyPairDataPoint.getValue(),
                currencyPair));
    }

    private void addCurrencyPair(CurrencyPairDataPoint currencyPairDataPoint) {
        currencyPairRepository.save(currencyPairDataPoint.getCurrencyPair());
    }
}
