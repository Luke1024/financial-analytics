package com.finance.repositoryshield;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CurrencyPairHistoryPointRepositoryShield {

    @Autowired
    private CurrencyPairHistoryPointRepository currencyPairHistoryPointRepository;

    @Autowired
    private CurrencyPairRepositoryShield currencyPairRepositoryShield;

    public Optional<CurrencyPairDataPoint> getLastDataPoint(long pair_id){
        return currencyPairHistoryPointRepository.getLastDataPoint(pair_id);
    }

    public Optional<CurrencyPairDataPoint> findPointByDate(LocalDateTime timeStamp, long pair_id){
        if(timeStamp != null){
            return currencyPairHistoryPointRepository.findPointByDate(timeStamp, pair_id);
        } else return Optional.empty();
    }

    public boolean save(CurrencyPairDataPoint currencyPairDataPoint, boolean overwrite){
        if(currencyPairDataPoint != null){
            return checkIfCurrencyPairNull(currencyPairDataPoint, overwrite);
        } else return false;
    }

    private boolean checkIfCurrencyPairNull(CurrencyPairDataPoint currencyPairDataPoint, boolean overwrite){
        CurrencyPair currencyPair = currencyPairDataPoint.getCurrencyPair();
        if(currencyPair != null){
            return checkIfCurrencyPairExistInDataBase(currencyPairDataPoint, overwrite);
        } else return false;
    }

    private boolean checkIfCurrencyPairExistInDataBase(CurrencyPairDataPoint currencyPairDataPoint, boolean overwrite){
        Optional<CurrencyPair> currencyPair = currencyPairRepositoryShield.findByCurrencyName(
                currencyPairDataPoint.getCurrencyPair().getCurrencyPairName());

        if(currencyPair.isPresent()){
            return addDataPoint(currencyPairDataPoint, currencyPair.get(), overwrite);
        } else return false;
    }

    private boolean addDataPoint(CurrencyPairDataPoint currencyPairDataPoint, CurrencyPair currencyPair, boolean overwrite) {
        Optional<CurrencyPairDataPoint> point = getCurrencyPairDataPoint(currencyPairDataPoint);
        if (point.isPresent() && overwrite) {
            overwriteDataPoint(point.get(), currencyPair);
        } else saveDataPoint()
    }

    private Optional<CurrencyPairDataPoint> getCurrencyPairDataPoint(CurrencyPairDataPoint currencyPairDataPoint) {
        return currencyPairHistoryPointRepository.findPointByDate(currencyPairDataPoint.getTimeStamp(),
                currencyPairDataPoint.getCurrencyPair().getId());
    }

    public void overwriteDataPoint(CurrencyPairDataPoint currencyPairDataPoint, CurrencyPair currencyPair){
        currencyPairHistoryPointRepository.save(new CurrencyPairDataPoint(currencyPairDataPoint.getTimeStamp(),
                currencyPairDataPoint.getValue(), currencyPair));
    }
}
