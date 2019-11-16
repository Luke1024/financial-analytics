package com.finance.data.domain.currency.dto;

import com.finance.data.domain.currency.LongShort;

public class OrderOpeningClosingDto {
    private Long userTradingAccountId;
    private LongShort longShort;
    private double lot;
    private double takeProfit;
    private double stopLoss;
    private String currencyPair;

    public OrderOpeningClosingDto() {
    }

    public OrderOpeningClosingDto(Long userTradingAccountId, LongShort longShort, double lot, double takeProfit, double stopLoss, String currencyPair) {
        this.userTradingAccountId = userTradingAccountId;
        this.longShort = longShort;
        this.lot = lot;
        this.takeProfit = takeProfit;
        this.stopLoss = stopLoss;
        this.currencyPair = currencyPair;
    }

    public Long getUserTradingAccountId() {
        return userTradingAccountId;
    }

    public LongShort getLongShort() {
        return longShort;
    }

    public double getLot() {
        return lot;
    }

    public double getTakeProfit() {
        return takeProfit;
    }

    public double getStopLoss() {
        return stopLoss;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }
}
