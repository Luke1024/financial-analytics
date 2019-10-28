package com.finance.tradingDataService.domain.dto;

public class CurrencyPairStatusDTO {
    private String currencyPairName;
    private double value;

    public CurrencyPairStatusDTO() {
    }

    public CurrencyPairStatusDTO(String currencyPairName, double value) {
        this.currencyPairName = currencyPairName;
        this.value = value;
    }

    public String getCurrencyPairName() {
        return currencyPairName;
    }

    public double getValue() {
        return value;
    }
}
