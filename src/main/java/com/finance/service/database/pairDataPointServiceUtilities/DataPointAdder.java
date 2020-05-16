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

    public DatabaseResponse addPoint(List<CurrencyPairDataPoint> currencyPairDataPoints, String currencyPairName, boolean overwrite){
        this.overwrite = overwrite;
        String log = "";
        for(CurrencyPairDataPoint point : currencyPairDataPoints){
            log += saveDataPoint(point, currencyPairName);
        }
        System.out.println("Log length " + log.length());
        if(log.length()==0){
            return new DatabaseResponse(null, "", true);
        } else return new DatabaseResponse(null, "AddPoint method in DataPointAdder failed. " + log, false);
    }

    private String saveDataPoint(CurrencyPairDataPoint point, String currencyPairName){
        return checkIfCurrencyPairDataPointOrAnySubobjectIsNull(point, currencyPairName);
    }

    private String checkIfCurrencyPairDataPointOrAnySubobjectIsNull(CurrencyPairDataPoint point, String currencyPairName){
        String log = "";

        //check main object
        log = checkIfCurrencyPairDataPointIsNull(point);

        //check subobject
        if(log.length() == 0) log = checkIfTimeStampIsNull(point);
        else return log;
        if(log.length() == 0) log = checkIfCurrencyPairIsNull(point);
        else return log;
        if(log.length() == 0) log = checkIfCurrencyPairNameIsNull(currencyPairName);
        else return log;;
        if(log.length() == 0) log = processWithSaving(point, currencyPairName);
        else return log;

        return log;
    }

    private String checkIfCurrencyPairDataPointIsNull(CurrencyPairDataPoint point){
        if(point == null) return "CurrencyPairDataPoint is null.";
        else return "";
    }

    private String checkIfTimeStampIsNull(CurrencyPairDataPoint point){
        if(point.getTimeStamp() == null) return "LocalDateTime timestamp in CurrencyPairDataPoint is null.";
        else return "";
    }

    private String checkIfCurrencyPairIsNull(CurrencyPairDataPoint point){
        if(point.getCurrencyPair() == null) return "CurrencyPair field in CurrencyPairDataPoint is null.";
        else return "";
    }

    private String checkIfCurrencyPairNameIsNull(String currencyPairName){
        if(currencyPairName == null) return
                "CurrencyPairName is null.";
        else return "";
    }




    private String processWithSaving(CurrencyPairDataPoint point, String currencyPair){
        String log = "";
        Optional<CurrencyPair> optionalPair = getCurrencyPair(currencyPair);
        if(optionalPair.isPresent()){
            return addPointToAlreadyExistingCurrency(point, optionalPair.get());
        } else {
            return "CurrencyPair not found.";
        }
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
        //point.setCurrencyPair(currencyPair);
        currencyPair.addDataPoint(point);

        //currencyPairRepository.save(currencyPair);
        repository.save(point);
    }
}
