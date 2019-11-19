package com.finance.data.domain.currency;

import com.finance.data.domain.accounts.UserTradingAccountHistoryPoint;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    private Long orderId;
    private LongShort longShort;
    private double lot;
    private String baseCurrency;
    private String currency;
    private double stopLoss;
    private double takeProfit;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CurrencyHistoryPoint currencyHistoryPointOpen;
    private LocalDateTime orderOpened;
    private double orderOpeningPrice;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CurrencyHistoryPoint currencyHistoryPointClose;
    private LocalDateTime orderClosed;
    private double orderClosingPrice;
    private double orderBalance;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserTradingAccountHistoryPoint userTradingAccountHistoryPoint;

    public Order() {}

    public Order(LongShort longShort, double lot, String baseCurrency, String currency, double stopLoss,
                 double takeProfit, CurrencyHistoryPoint currencyHistoryPointOpen, LocalDateTime orderOpened,
                 double orderOpeningPrice, CurrencyHistoryPoint currencyHistoryPointClose, LocalDateTime orderClosed,
                 double orderClosingPrice, double orderBalance, UserTradingAccountHistoryPoint userTradingAccountHistoryPoint) {
        this.longShort = longShort;
        this.lot = lot;
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.stopLoss = stopLoss;
        this.takeProfit = takeProfit;
        this.currencyHistoryPointOpen = currencyHistoryPointOpen;
        this.orderOpened = orderOpened;
        this.orderOpeningPrice = orderOpeningPrice;
        this.currencyHistoryPointClose = currencyHistoryPointClose;
        this.orderClosed = orderClosed;
        this.orderClosingPrice = orderClosingPrice;
        this.orderBalance = orderBalance;
        this.userTradingAccountHistoryPoint = userTradingAccountHistoryPoint;
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

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public double getStopLoss() {
        return stopLoss;
    }

    public double getTakeProfit() {
        return takeProfit;
    }

    public CurrencyHistoryPoint getCurrencyHistoryPointOpen() {
        return currencyHistoryPointOpen;
    }

    public LocalDateTime getOrderOpened() {
        return orderOpened;
    }

    public double getOrderOpeningPrice() {
        return orderOpeningPrice;
    }

    public CurrencyHistoryPoint getCurrencyHistoryPointClose() {
        return currencyHistoryPointClose;
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

    public UserTradingAccountHistoryPoint getUserTradingAccountHistoryPoint() {
        return userTradingAccountHistoryPoint;
    }
}
