package com.finance.tradingDataService.domain;

public class CurrencyPair {
    private String currencyPairName;
    private double value;

    public CurrencyPair() {
    }

    public CurrencyPair(String currencyPair, double value) {
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
