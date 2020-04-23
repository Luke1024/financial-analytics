package com.finance.preprocessor.utilities;

import com.finance.domain.CurrencyPairHistoryPoint;
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
        Curre

        List<CurrencyPairHistoryPoint> historyPoints = mapDataPointsToHistoryPoints(dataPointList);


    }

    private List<CurrencyPairHistoryPoint> mapDataPointsToHistoryPoints(List<DataPoint> dataPoints){
        List<CurrencyPairHistoryPoint> currencyPairHistoryPoints = new ArrayList<>();
        for(DataPoint dataPoint : dataPoints){
            currencyPairHistoryPoints.add(new CurrencyPairHistoryPoint());
        }
        return currencyPairHistoryPoints;
    }
}
