package com.finance.domain.dto;

public class CurrencyOverviewDto {
    private String pairName;
    private double value;

    public CurrencyOverviewDto(String pairName, double value) {
        this.pairName = pairName;
        this.value = value;
    }

    public String getPairName() {
        return pairName;
    }

    public double getValue() {
        return value;
    }
}
