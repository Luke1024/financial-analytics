package com.finance.service.database.pairDataPointServiceUtilities;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import com.finance.service.database.communicationObjects.DatabaseResponse;
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

    public DatabaseResponse addPoint(List<CurrencyPairDataPoint> currencyPairDataPoints, boolean overwrite){
        this.overwrite = overwrite;
        String log = "";
        for(CurrencyPairDataPoint point : currencyPairDataPoints){
            log += saveDataPoint(point);
        }
        if(log.length()==0){
            return new DatabaseResponse(null, "", true);
        }
        return new DatabaseResponse(null, "AddPoint method in DataPointAdder failed. " + log, false);
    }

    private String saveDataPoint(CurrencyPairDataPoint point) {
        if(point != null) {
            return checkIfCurrencyPairNotNull(point);
        } else {
            return "CurrencyPairDataPoint is null.";
        }
    }

    private String checkIfCurrencyPairNotNull(CurrencyPairDataPoint point) {
        if(point.getCurrencyPair() != null){
            return checkIfCurrencyNameNotNull(point);
        } else {
            return "CurrencyPair field in CurrencyPairDataPoint is null.";
        }
    }

    private String checkIfCurrencyNameNotNull(CurrencyPairDataPoint point) {
        if(point.getCurrencyPair().getCurrencyPairName() != null){
            return checkIfCurrencyPairExistInDatabase(point);
        } else {
            return "CurrencyPairName field in CurrencyPair in CurrencyPairDataPoint is null.";
        }
    }

    private String checkIfCurrencyPairExistInDatabase(CurrencyPairDataPoint point) {
        String currencyPairName = point.getCurrencyPair().getCurrencyPairName();
        Optional<CurrencyPair> currencyPair = currencyPairRepository.findByCurrencyName(currencyPairName);
        if(currencyPair.isPresent()) {
            return checkIfPointWithTheSameTimeStampAlreadyExistInDatabase(currencyPair.get(), point);
        }
        else {
            return "CurrencyPair not found.";
        }
    }

    private String checkIfPointWithTheSameTimeStampAlreadyExistInDatabase(CurrencyPair currencyPair, CurrencyPairDataPoint point){
        Optional<CurrencyPairDataPoint> dataPoint = repository.findPointByDate(point.getTimeStamp(), currencyPair.getId());
        if(dataPoint.isPresent()){
            if(this.overwrite == true) {
                if(deleteDataPoint(dataPoint.get())){
                    addPointToCurrencyPair(currencyPair, point);
                } else return "CurrencyPairDataPoint with the same timestamp exist and there is problem with overwriting.";
            }
        } else {
            addPointToCurrencyPair(currencyPair, point);
            if(point.getPointId() != null) return "";
            else return "CurrencyPair saving execution problem.";
        }
        return "CheckIfPointWithTheSameTimeStampAlreadyExistsInDatabase in DataPointAdder failed.";
    }

    private boolean deleteDataPoint(CurrencyPairDataPoint dataPoint){
        repository.deleteById(dataPoint.getPointId());
        if(repository.findById(dataPoint.getPointId()).isPresent()) return false;
        else return true;
    }

    private void addPointToCurrencyPair(CurrencyPair currencyPair, CurrencyPairDataPoint currencyPairDataPoint) {
        List<CurrencyPairDataPoint> pairDataPoints = currencyPair.getCurrencyPairDataPoints();
        pairDataPoints.add(currencyPairDataPoint);
        currencyPair.setCurrencyPairDataPoints(pairDataPoints);
        //currencyPairRepository.save(currencyPair);
        repository.save(new CurrencyPairDataPoint(currencyPairDataPoint.getTimeStamp(), currencyPairDataPoint.getValue(),
                currencyPair));
    }
}
