package com.finance.preprocessor.utilities;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class CurrencyPairDataPack {
    private String currencyPairName;
    private ChronoUnit timeFrame;
    private List<DataPoint> dataPointList;

    public CurrencyPairDataPack(String currencyPairName, ChronoUnit timeFrame, List<DataPoint> dataPointList) {
        this.currencyPairName = currencyPairName;
        this.timeFrame = timeFrame;
        this.dataPointList = dataPointList;
    }

    public String getCurrencyPairName() {
        return currencyPairName;
    }

    public ChronoUnit getTimeFrame() {
        return timeFrame;
    }

    public List<DataPoint> getDataPointList() {
        return dataPointList;
    }

    @Override
    public String toString() {
        return "CurrencyPairDataPack{" +
                "currencyPairName='" + currencyPairName + '\'' +
                ", timeFrame=" + timeFrame +
                ", dataPointList=" + dataPointList +
                '}';
    }
}
