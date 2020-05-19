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

@Service
public class DataBaseLoader {

    @Autowired
    private CurrencyPairService currencyPairService;

    @Autowired
    private CurrencyPairDataPointService dataPointService;

    public void load(List<CurrencyPairDataPack> currencyPairDataPacks){

        for(CurrencyPairDataPack pack : currencyPairDataPacks){
            loadPackToDatabase(pack);
        }
    }

    private void loadPackToDatabase(CurrencyPairDataPack pack){
        String currencyPairName = pack.getCurrencyPairName();
        List<DataPoint> dataPointList = pack.getDataPointList();
        List<CurrencyPairDataPoint> currencyPairDataPoints = mapDataPointsToCurrencyPairDataPoints(dataPointList);

        if(isCurrencyAlreadyExistInDatabase(currencyPairName)){
            System.out.println("CurrencyPair with name: " + currencyPairName + " already exist in database.");
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
        CurrencyPair newCurrencyPair = new CurrencyPair(currencyPairName);
        DatabaseResponse response = currencyPairService.saveCurrencyPair(newCurrencyPair, false);
        if(response.isOK()) return true;
        else return false;
    }

    private void loadDataPoints(List<CurrencyPairDataPoint> dataPoints, String currencyPairName){
        dataPointService.addDataPoints(dataPoints, currencyPairName);
    }
}
