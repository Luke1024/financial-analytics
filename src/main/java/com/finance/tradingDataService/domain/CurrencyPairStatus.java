package com.finance.tradingDataService.domain;

public class CurrencyPairStatus {
    private String currencyPairName;
    private double value;

    public CurrencyPairStatus() {
    }

    public CurrencyPairStatus(String currencyPair, double value) {
        this.currencyPairName = currencyPair;
        this.value = value;
    }

    public String getCurrencyPairName() {
        return currencyPairName;
    }

    public double getValue() {
        return value;
    }
}
