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
    private String baseCurrency;
    private String currency;
    private double stopLoss;
    private double takeProfit;
    private CurrencyHistoryPoint currencyHistoryPointOpen;
    private LocalDateTime orderOpened;
    private double orderOpeningPrice;
    private CurrencyHistoryPoint currencyHistoryPointClose;
    private LocalDateTime orderClosed;
    private double orderClosingPrice;
    private double orderBalance;
    private UserTradingAccount userTradingAccount;

    public Order() {
    }

    public Order(LongShort longShort, double lot, String baseCurrency, String currency, double stopLoss, double takeProfit,
                 CurrencyHistoryPoint currencyHistoryPointOpen, LocalDateTime orderOpened, double orderOpeningPrice,
                 CurrencyHistoryPoint currencyHistoryPointClose, LocalDateTime orderClosed, double orderClosingPrice,
                 double orderBalance, UserTradingAccount userTradingAccount) {
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

    public UserTradingAccount getUserTradingAccount() {
        return userTradingAccount;
    }
}
