package com.finance.preprocessor.utilities;

import com.finance.domain.CurrencyPairDataPoint;
import com.finance.service.database.CurrencyPairService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DataBaseLoader {

    @Autowired
    private CurrencyPairService currencyPairService;

    public void load(List<CurrencyPairDataPack> currencyPairDataPacks){

        for(CurrencyPairDataPack pack : currencyPairDataPacks){
            loadPackToDatabase(pack);
        }
    }

    private void loadPackToDatabase(CurrencyPairDataPack pack){
        String currencyPairName = pack.getCurrencyPairName();
        List<DataPoint> dataPointList = pack.getDataPointList();
        //Curre

        List<CurrencyPairDataPoint> historyPoints = mapDataPointsToHistoryPoints(dataPointList);


    }

    private List<CurrencyPairDataPoint> mapDataPointsToHistoryPoints(List<DataPoint> dataPoints){
        List<CurrencyPairDataPoint> currencyPairDataPoints = new ArrayList<>();
        for(DataPoint dataPoint : dataPoints){
            currencyPairDataPoints.add(new CurrencyPairDataPoint());
        }
        return currencyPairDataPoints;
    }
}
