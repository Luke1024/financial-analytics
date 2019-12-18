package com.finance.data.domain.currency;

import com.finance.data.domain.currency.enums.LongShort;

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
    private String currencyPair;
    private double stopLoss;
    private double takeProfit;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CurrencyPairHistoryPoint currencyPairHistoryPointOpen;
    private LocalDateTime orderOpened;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CurrencyPairHistoryPoint currencyPairHistoryPointClose;
    private LocalDateTime orderClosed;
    private double orderBalance;

    public Order() {}

    public Order(Long orderId) {
        this.orderId = orderId;
    }

    public Order(LongShort longShort, double lot, String currencyPair, double stopLoss, double takeProfit,
                 CurrencyPairHistoryPoint currencyPairHistoryPointOpen, LocalDateTime orderOpened,
                 CurrencyPairHistoryPoint currencyPairHistoryPointClose, LocalDateTime orderClosed, double orderBalance) {
        this.longShort = longShort;
        this.lot = lot;
        this.currencyPair = currencyPair;
        this.stopLoss = stopLoss;
        this.takeProfit = takeProfit;
        this.currencyPairHistoryPointOpen = currencyPairHistoryPointOpen;
        this.orderOpened = orderOpened;
        this.currencyPairHistoryPointClose = currencyPairHistoryPointClose;
        this.orderClosed = orderClosed;
        this.orderBalance = orderBalance;
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

    public CurrencyPairHistoryPoint getCurrencyPairHistoryPointOpen() {
        return currencyPairHistoryPointOpen;
    }

    public LocalDateTime getOrderOpened() {
        return orderOpened;
    }

    public CurrencyPairHistoryPoint getCurrencyPairHistoryPointClose() {
        return currencyPairHistoryPointClose;
    }

    public LocalDateTime getOrderClosed() {
        return orderClosed;
    }

    public double getOrderBalance() {
        return orderBalance;
    }
}
