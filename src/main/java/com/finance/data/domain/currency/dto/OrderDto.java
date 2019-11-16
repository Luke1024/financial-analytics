package com.finance.data.domain.currency.dto;

import com.finance.data.domain.currency.CurrencyHistoryPointDto;
import com.finance.data.domain.currency.LongShort;

import java.time.LocalDateTime;

public class OrderDto {
    private Long orderId;
    private LongShort longShort;
    private double lot;
    private String currencyPair;
    private double stopLoss;
    private double takeProfit;
    private CurrencyHistoryPointDto currencyHistoryPointOpen;
    private LocalDateTime orderOpened;
    private CurrencyHistoryPointDto currencyHistoryPointClose;
    private LocalDateTime orderClosed;
    private double orderBalance;
    private Long userTradingAccountId;

    public OrderDto() {}

    public OrderDto(Long orderId, LongShort longShort, double lot, String currencyPair, double stopLoss,
                    double takeProfit, CurrencyHistoryPointDto currencyHistoryPointOpen, LocalDateTime orderOpened,
                    CurrencyHistoryPointDto currencyHistoryPointClose, LocalDateTime orderClosed, double orderBalance,
                    Long userTradingAccountId) {
        this.orderId = orderId;
        this.longShort = longShort;
        this.lot = lot;
        this.currencyPair = currencyPair;
        this.stopLoss = stopLoss;
        this.takeProfit = takeProfit;
        this.currencyHistoryPointOpen = currencyHistoryPointOpen;
        this.orderOpened = orderOpened;
        this.currencyHistoryPointClose = currencyHistoryPointClose;
        this.orderClosed = orderClosed;
        this.orderBalance = orderBalance;
        this.userTradingAccountId = userTradingAccountId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public LongShort getLongShort() {
        return longShort;
    }

    public double getLot() {
        return lot;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public double getStopLoss() {
        return stopLoss;
    }

    public double getTakeProfit() {
        return takeProfit;
    }

    public CurrencyHistoryPointDto getCurrencyHistoryPointOpen() {
        return currencyHistoryPointOpen;
    }

    public LocalDateTime getOrderOpened() {
        return orderOpened;
    }

    public CurrencyHistoryPointDto getCurrencyHistoryPointClose() {
        return currencyHistoryPointClose;
    }

    public LocalDateTime getOrderClosed() {
        return orderClosed;
    }

    public double getOrderBalance() {
        return orderBalance;
    }

    public Long getUserTradingAccountId() {
        return userTradingAccountId;
    }
}
