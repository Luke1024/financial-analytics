package com.finance.data.domain.currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long transactionId;
    private Long transactionType;
    private CurrencyHistoryPoint currencyHistoryPoint;

    public Transaction() {
    }

    public Transaction(Long transactionId, Long transactionType, CurrencyHistoryPoint currencyHistoryPoint) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.currencyHistoryPoint = currencyHistoryPoint;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public Long getTransactionType() {
        return transactionType;
    }

    public CurrencyHistoryPoint getCurrencyHistoryPoint() {
        return currencyHistoryPoint;
    }
}
