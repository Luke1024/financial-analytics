package com.finance.tradingDataService.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CurrencyHistoryPoint {
    @Id
    @GeneratedValue
    private Long pointId;
    private LocalDateTime timeStamp;
    private Double value;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRENCY_ID")
    private Currency currency;

    public CurrencyHistoryPoint(LocalDateTime timeStamp, Double value, Currency currency) {
        this.timeStamp = timeStamp;
        this.value = value;
        this.currency = currency;
    }

    public Long getPointId() {
        return pointId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public Double getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }
}
