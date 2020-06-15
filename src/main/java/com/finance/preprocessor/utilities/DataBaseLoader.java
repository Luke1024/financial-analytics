package com.finance.preprocessor.utilities;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.service.database.CurrencyPairDataPointService;
import com.finance.service.database.CurrencyPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DataBaseLoader {

    private Logger logger = Logger.getLogger(DataBaseLoader.class.getName());

    @Autowired
    private CurrencyPairService currencyPairService;

    @Autowired
    private CurrencyPairDataPointService dataPointService;

    public void load(List<CurrencyPairDataPack> currencyPairDataPacks){

        if(currencyPairDataPacks != null) {
            for (CurrencyPairDataPack pack : currencyPairDataPacks) {
                loadPackToDatabase(pack);
            }
        } else {
            logger.log(Level.WARNING, "CurrencyPairDataPack is null.");
        }
    }

    private void loadPackToDatabase(CurrencyPairDataPack pack){
        if(pack == null){
            logger.log(Level.WARNING, "CurrencyPairDataPack is null.");
            return;
        }
        if(pack.getCurrencyPairName() == null){
            logger.log(Level.WARNING, "CurrencyPairName in DataPack is null.");
            return;
        }
        String currencyPairName = pack.getCurrencyPairName();
        if(pack.getDataPointList() == null){
            logger.log(Level.WARNING, "DataPointList in DataPack is null.");
        }
        List<DataPoint> dataPointList = pack.getDataPointList();

        List<CurrencyPairDataPoint> currencyPairDataPoints = mapDataPointsToCurrencyPairDataPoints(dataPointList);

        if(isCurrencyAlreadyExistInDatabase(currencyPairName)){
            logger.log(Level.SEVERE,"CurrencyPair with name: " + currencyPairName +
                    " already exist in database. Stopping DataBaseLoader.");
        } else {
            if(createCurrencyPair(currencyPairName)) {
                loadDataPoints(currencyPairDataPoints, currencyPairName);
            }
        }
    }

    private List<CurrencyPairDataPoint> mapDataPointsToCurrencyPairDataPoints(List<DataPoint> dataPoints){
        List<CurrencyPairDataPoint> currencyPairDataPoints = new ArrayList<>();
        for(DataPoint dataPoint : dataPoints){
            currencyPairDataPoints.add(new CurrencyPairDataPoint(dataPoint.getLocalDateTime(), dataPoint.getValue()));
        }
        return currencyPairDataPoints;
    }

    private boolean isCurrencyAlreadyExistInDatabase(String currencyPairName){
        CurrencyPair currencyPair = currencyPairService.getCurrencyPair(currencyPairName);
        return currencyPair != null;
    }

    private boolean createCurrencyPair(String currencyPairName){
        if(currencyPairName != null) {
            CurrencyPair newCurrencyPair = new CurrencyPair(currencyPairName);
            currencyPairService.saveCurrencyPair(newCurrencyPair, false);
            if (checkIfCurrencyPairExist(currencyPairName)) {
                logger.log(Level.INFO, currencyPairName + " saved.");
                return true;
            } else {
                logger.log(Level.SEVERE, "Problem with saving " + currencyPairName + ". Stopping DataBaseLoader.");
            }
        } else {
            logger.log(Level.SEVERE, "CurrencyPair name is null. Stopping DataBaseLoader.");
        }
        return false;
    }

    private boolean checkIfCurrencyPairExist(String currencyPairName){
        CurrencyPair currencyPair = currencyPairService.getCurrencyPair(currencyPairName);
        return currencyPair != null;
    }

    private void loadDataPoints(List<CurrencyPairDataPoint> dataPoints, String currencyPairName){
        if(dataPoints != null){
            if(dataPoints.size() > 0){
                dataPointService.addDataPoints(dataPoints, currencyPairName);
            } else {
                logger.log(Level.SEVERE, "There is no datapoints to be saved.");
            }
        } else {
            logger.log(Level.SEVERE, "CurrencyPairDataPoint list is null. Stopping DataBase loader.");
        }
    }
}
