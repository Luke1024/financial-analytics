package com.finance.data.domain.currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long orderId;
    private CurrencyHistoryPoint currencyHistoryPoint;

    public Order() {
    }

    public Order(Long orderId, CurrencyHistoryPoint currencyHistoryPoint) {
        this.orderId = orderId;
        this.currencyHistoryPoint = currencyHistoryPoint;
    }

    public Long getOrderId() {
        return orderId;
    }

    public CurrencyHistoryPoint getCurrencyHistoryPoint() {
        return currencyHistoryPoint;
    }
}
