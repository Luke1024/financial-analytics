package com.finance.tradingDataService.domain.dto;

public class HistoryPoint {
    private String[] pricePoint;

    public HistoryPoint() {
    }

    public HistoryPoint(String[] pricePoint) {
        this.pricePoint = pricePoint;
    }

    public String[] getPricePoint() {
        return pricePoint;
    }
}
