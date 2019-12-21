package com.finance.domain.dto;

import com.finance.domain.enums.LongShort;

import java.time.LocalDateTime;

public class OrderDto {
    private Long orderId;
    private LongShort longShort;
    private double lot;
    private String currencyPair;
    private double stopLoss;
    private double takeProfit;
    private Long currencyHistoryPointOpenId;
    private LocalDateTime orderOpened;
    private double orderOpeningPrice;
    private Long currencyHistoryPointCloseId;
    private LocalDateTime orderClosed;
    private double orderClosingPrice;
    private double orderBalance;
    private Long userTradingAccountId;

    public OrderDto() {}

    public OrderDto(Long orderId, LongShort longShort, double lot, String currencyPair, double stopLoss,
                    double takeProfit, Long currencyHistoryPointOpenId, LocalDateTime orderOpened,
                    double orderOpeningPrice, Long currencyHistoryPointCloseId, LocalDateTime orderClosed,
                    double orderClosingPrice, double orderBalance, Long userTradingAccountId) {
        this.orderId = orderId;
        this.longShort = longShort;
        this.lot = lot;
        this.currencyPair = currencyPair;
        this.stopLoss = stopLoss;
        this.takeProfit = takeProfit;
        this.currencyHistoryPointOpenId = currencyHistoryPointOpenId;
        this.orderOpened = orderOpened;
        this.orderOpeningPrice = orderOpeningPrice;
        this.currencyHistoryPointCloseId = currencyHistoryPointCloseId;
        this.orderClosed = orderClosed;
        this.orderClosingPrice = orderClosingPrice;
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

    public Long getCurrencyHistoryPointOpenId() {
        return currencyHistoryPointOpenId;
    }

    public LocalDateTime getOrderOpened() {
        return orderOpened;
    }

    public double getOrderOpeningPrice() {
        return orderOpeningPrice;
    }

    public Long getCurrencyHistoryPointCloseId() {
        return currencyHistoryPointCloseId;
    }

    public LocalDateTime getOrderClosed() {
        return orderClosed;
    }

    public double getOrderClosingPrice() {
        return orderClosingPrice;
    }

    public double getOrderBalance() {
        return orderBalance;
    }

    public Long getUserTradingAccountId() {
        return userTradingAccountId;
    }
}
