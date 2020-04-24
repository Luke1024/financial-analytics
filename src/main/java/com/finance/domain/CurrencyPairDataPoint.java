package com.finance.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedNativeQuery(
        name="CurrencyPairHistoryPoint.retrieveByTimeRangeAndCurrencyName",
        query="SELECT * FROM currency_history_point" +
                " WHERE time_stamp => :TIME_STAMP_START AND " +
                "time_stamp <= :TIME_STAMP_STOP AND currency_pair_name = :CURRENCY_ID;",
        resultClass = CurrencyPair.class
)

@Entity
public class CurrencyPairDataPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pointId;
    private LocalDateTime timeStamp;
    private Double value;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRENCY_PAIR_ID")
    private CurrencyPair currencyPair;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Order order;

    public CurrencyPairDataPoint() {
    }

    public CurrencyPairDataPoint(LocalDateTime timeStamp, Double value, CurrencyPair currencyPair) {
        this.timeStamp = timeStamp;
        this.value = value;
        this.currencyPair = currencyPair;
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

    public CurrencyPair getCurrencyPair() {
        return currencyPair;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
