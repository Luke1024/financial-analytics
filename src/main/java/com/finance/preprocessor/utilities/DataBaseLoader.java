package com.finance.preprocessor.utilities;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.service.database.CurrencyPairDataPointService;
import com.finance.service.database.CurrencyPairService;
import com.finance.service.database.communicationObjects.DatabaseResponse;
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
        String currencyPairName = pack.getCurrencyPairName();
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
        DatabaseResponse databaseResponse = currencyPairService.getCurrencyPair(currencyPairName);
        if(databaseResponse.getLog().contains("not found")) return false;
        else return true;
    }

    private boolean createCurrencyPair(String currencyPairName){
        if(currencyPairName != null) {
            CurrencyPair newCurrencyPair = new CurrencyPair(currencyPairName);
            DatabaseResponse response = currencyPairService.saveCurrencyPair(newCurrencyPair, false);
            if (response.isOK()) {
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
