package com.finance.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
@NamedNativeQuery(
        name="CurrencyPairHistoryPoint.retrieveByTimeRangeAndCurrencyName",
        query="SELECT * FROM currency_history_point" +
                " WHERE time_stamp => :TIME_STAMP_START AND " +
                "time_stamp <= :TIME_STAMP_STOP AND currency_pair_name = :CURRENCY_ID;",
        resultClass = CurrencyPair.class
)
 */

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "CurrencyPairDataPoint.getLastDataPoint",
                query = "SELECT * FROM trading_data_microservice.currency_pair_data_point " +
                        "WHERE currency_pair_id = :PAIR_ID" +
                        " ORDER BY time_stamp DESC LIMIT 1;",
                resultClass = CurrencyPairDataPoint.class),

        @NamedNativeQuery(
                name = "CurrencyPairDataPoint.findPointByDate",
                query = "SELECT * FROM trading_data_microservice.currency_pair_data_point " +
                        "WHERE time_stamp = :TIME_STAMP AND currency_pair_id = :PAIR_ID",
                resultClass = CurrencyPairDataPoint.class)
})

@Entity
public class CurrencyPairDataPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pointId;
    @Column(unique = true)
    private LocalDateTime timeStamp;
    private Double value;
    @ManyToOne(cascade = CascadeType.MERGE)
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

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setCurrencyPair(CurrencyPair currencyPair) {
        this.currencyPair = currencyPair;
    }

    @Override
    public String toString() {
        return "CurrencyPairDataPoint{" +
                "pointId=" + pointId +
                ", timeStamp=" + timeStamp +
                ", value=" + value +
                ", currencyPair=" + currencyPair +
                ", order=" + order +
                '}';
    }
}
