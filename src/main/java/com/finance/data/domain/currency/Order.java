package com.finance.data.domain.currency;

import com.finance.data.domain.accounts.UserTradingAccount;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long orderId;
    private LongShort longShort;
    private double lot;
    private String currencyPair;
    private double stopLoss;
    private double takeProfit;
    private CurrencyHistoryPoint currencyHistoryPointOpen;
    private LocalDateTime orderOpened;
    private CurrencyHistoryPoint currencyHistoryPointClose;
    private LocalDateTime orderClosed;
    private double orderBalance;
    private UserTradingAccount userTradingAccount;

    public Order() {
    }

    public Order(LongShort longShort, double lot, String currencyPair, double stopLoss, double takeProfit, CurrencyHistoryPoint currencyHistoryPointOpen, LocalDateTime orderOpened, CurrencyHistoryPoint currencyHistoryPointClose, LocalDateTime orderClosed, double orderBalance, UserTradingAccount userTradingAccount) {
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
        this.userTradingAccount = userTradingAccount;
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

    public CurrencyHistoryPoint getCurrencyHistoryPointOpen() {
        return currencyHistoryPointOpen;
    }

    public LocalDateTime getOrderOpened() {
        return orderOpened;
    }

    public CurrencyHistoryPoint getCurrencyHistoryPointClose() {
        return currencyHistoryPointClose;
    }

    public LocalDateTime getOrderClosed() {
        return orderClosed;
    }

    public double getOrderBalance() {
        return orderBalance;
    }

    public UserTradingAccount getUserTradingAccount() {
        return userTradingAccount;
    }
}
