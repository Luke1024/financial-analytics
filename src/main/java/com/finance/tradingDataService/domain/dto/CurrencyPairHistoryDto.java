package com.finance.tradingDataService.domain.dto;

public class CurrencyPairHistoryDto {
    private String symbol;
    private HistoryPoint[] historyPoint;

    public CurrencyPairHistoryDto() {
    }

    public CurrencyPairHistoryDto(String symbol, HistoryPoint[] historyPoint) {
        this.symbol = symbol;
        this.historyPoint = historyPoint;
    }

    public String getSymbol() {
        return symbol;
    }

    public HistoryPoint[] getHistoryPoint() {
        return historyPoint;
    }
}
