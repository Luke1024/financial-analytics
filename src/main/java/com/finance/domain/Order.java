package com.finance.domain;

import com.finance.domain.enums.LongShort;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedNativeQuery(
        name="Order.findOrderByOrderClosedNull",
        query="SELECT * FROM orders WHERE trading_account_id = :TRADING_ACCOUNT_ID;",
        resultClass = Order.class
)

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
    private CurrencyPairDataPoint currencyPairDataPointOpen;
    private LocalDateTime orderOpened;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CurrencyPairDataPoint currencyPairDataPointClose;
    private LocalDateTime orderClosed;
    private double orderBalance;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TRADING_ACCOUNT_ID")
    private TradingAccount tradingAccount;

    public Order() {}

    public Order(Long orderId) {
        this.orderId = orderId;
    }

    public Order(LongShort longShort, double lot, String currencyPair, double stopLoss, double takeProfit,
                 CurrencyPairDataPoint currencyPairDataPointOpen, LocalDateTime orderOpened,
                 CurrencyPairDataPoint currencyPairDataPointClose, LocalDateTime orderClosed, double orderBalance) {
        this.longShort = longShort;
        this.lot = lot;
        this.currencyPair = currencyPair;
        this.stopLoss = stopLoss;
        this.takeProfit = takeProfit;
        this.currencyPairDataPointOpen = currencyPairDataPointOpen;
        this.orderOpened = orderOpened;
        this.currencyPairDataPointClose = currencyPairDataPointClose;
        this.orderClosed = orderClosed;
        this.orderBalance = orderBalance;
    }

    public void setTradingAccount(TradingAccount tradingAccount) {
        this.tradingAccount = tradingAccount;
    }

    public void setOrderClosed(LocalDateTime orderClosed) {
        this.orderClosed = orderClosed;
    }

    public void setOrderBalance(double orderBalance) {
        this.orderBalance = orderBalance;
    }

    public void setStopLoss(double stopLoss) {
        this.stopLoss = stopLoss;
    }

    public void setTakeProfit(double takeProfit) {
        this.takeProfit = takeProfit;
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

    public CurrencyPairDataPoint getCurrencyPairDataPointOpen() {
        return currencyPairDataPointOpen;
    }

    public LocalDateTime getOrderOpened() {
        return orderOpened;
    }

    public CurrencyPairDataPoint getCurrencyPairDataPointClose() {
        return currencyPairDataPointClose;
    }

    public LocalDateTime getOrderClosed() {
        return orderClosed;
    }

    public double getOrderBalance() {
        return orderBalance;
    }
}
