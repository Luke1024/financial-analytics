package com.finance.tradingDataService.domain.dto;

public class CurrencyValue {
    private String currencySymbol;
    private String base;
    private Double value;

    public CurrencyValue(String currencySymbol, String base, Double value) {
        this.currencySymbol = currencySymbol;
        this.base = base;
        this.value = value;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public String getBase() {
        return base;
    }

    public Double getValue() {
        return value;
    }
}
