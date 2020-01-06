package com.finance.domain.dto;

import java.time.LocalDateTime;

public class CurrencyPairOverviewDto {
    private LocalDateTime timeStamp;
    private String currencyPairName;
    private double price;

    public CurrencyPairOverviewDto(LocalDateTime timeStamp, String currencyPairName, double price) {
        this.timeStamp = timeStamp;
        this.currencyPairName = currencyPairName;
        this.price = price;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getCurrencyPairName() {
        return currencyPairName;
    }

    public double getPrice() {
        return price;
    }
}
