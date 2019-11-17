package com.finance.data.domain.currency.dto;

import com.finance.data.domain.currency.LongShort;

public class OrderOpeningDto {
    private Long userTradingAccountId;
    private LongShort longShort;
    private double lot;
    private double takeProfit;
    private double stopLoss;
    private String baseCurrency;
    private String currency;

    public OrderOpeningDto() {
    }

    public OrderOpeningDto(Long userTradingAccountId, LongShort longShort, double lot, double takeProfit, double stopLoss, String baseCurrency, String currency) {
        this.userTradingAccountId = userTradingAccountId;
        this.longShort = longShort;
        this.lot = lot;
        this.takeProfit = takeProfit;
        this.stopLoss = stopLoss;
        this.baseCurrency = baseCurrency;
        this.currency = currency;
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

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }
}
