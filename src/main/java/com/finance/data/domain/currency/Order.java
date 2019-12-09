package com.finance.data.domain.currency;

import com.finance.data.domain.accounts.TradingAccountHistoryPoint;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private LongShort longShort;
    private double lot;
    private String baseCurrency;
    private String currency;
    private double stopLoss;
    private double takeProfit;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CurrencyPairHistoryPoint currencyPairHistoryPointOpen;
    private LocalDateTime orderOpened;
    private double orderOpeningPrice;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CurrencyPairHistoryPoint currencyPairHistoryPointClose;
    private LocalDateTime orderClosed;
    private double orderClosingPrice;
    private double orderBalance;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TradingAccountHistoryPoint tradingAccountHistoryPoint;

    public Order() {}

    public Order(Long orderId) {
        this.orderId = orderId;
    }

    public Order(LongShort longShort, double lot, String baseCurrency, String currency, double stopLoss,
                 double takeProfit, CurrencyPairHistoryPoint currencyPairHistoryPointOpen, LocalDateTime orderOpened,
                 double orderOpeningPrice, CurrencyPairHistoryPoint currencyPairHistoryPointClose, LocalDateTime orderClosed,
                 double orderClosingPrice, double orderBalance, TradingAccountHistoryPoint tradingAccountHistoryPoint) {
        this.longShort = longShort;
        this.lot = lot;
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.stopLoss = stopLoss;
        this.takeProfit = takeProfit;
        this.currencyPairHistoryPointOpen = currencyPairHistoryPointOpen;
        this.orderOpened = orderOpened;
        this.orderOpeningPrice = orderOpeningPrice;
        this.currencyPairHistoryPointClose = currencyPairHistoryPointClose;
        this.orderClosed = orderClosed;
        this.orderClosingPrice = orderClosingPrice;
        this.orderBalance = orderBalance;
        this.tradingAccountHistoryPoint = tradingAccountHistoryPoint;
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

    public CurrencyPairHistoryPoint getCurrencyPairHistoryPointOpen() {
        return currencyPairHistoryPointOpen;
    }

    public LocalDateTime getOrderOpened() {
        return orderOpened;
    }

    public double getOrderOpeningPrice() {
        return orderOpeningPrice;
    }

    public CurrencyPairHistoryPoint getCurrencyPairHistoryPointClose() {
        return currencyPairHistoryPointClose;
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

    public TradingAccountHistoryPoint getTradingAccountHistoryPoint() {
        return tradingAccountHistoryPoint;
    }
}
