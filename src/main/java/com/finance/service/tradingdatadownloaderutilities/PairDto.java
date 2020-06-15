package com.finance.service.tradingdatadownloaderutilities;

public class PairDto {
    private String pairName;
    private double value;

    public PairDto(String pairName, double value) {
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
