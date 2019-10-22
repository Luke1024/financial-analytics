package com.finance.tradingDataService.domain.instruments;

import com.finance.tradingDataService.domain.instrumentHistoryPoints.StockHistoryPoint;

import java.util.List;

public class Stock {
    private String symbol;
    private String name;
    private List<StockHistoryPoint> stockHistoryPointList;

    public Stock(String symbol, String name, List<StockHistoryPoint> stockHistoryPointList) {
        this.symbol = symbol;
        this.name = name;
        this.stockHistoryPointList = stockHistoryPointList;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public List<StockHistoryPoint> getStockHistoryPointList() {
        return stockHistoryPointList;
    }
}
