package com.finance.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;



@NamedNativeQuery(
                name = "CurrencyPairDataPoint.findPointByDate",
                query = "SELECT * FROM trading_data_microservice.currency_pair_data_point " +
                        "WHERE time_stamp = :TIME_STAMP AND currency_pair_id = :PAIR_ID",
                resultClass = CurrencyPairDataPoint.class
)
@NamedNativeQuery(
        name = "CurrencyPairDataPoint.getLastDataPoint",
        query = "SELECT * FROM trading_data_microservice.currency_pair_data_point " +
                "WHERE currency_pair_id = :PAIR_ID" +
                " ORDER BY time_stamp DESC LIMIT 1;",
        resultClass = CurrencyPairDataPoint.class)

@Entity
public class CurrencyPairDataPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pointId;
    //@Column(unique = true)
    private LocalDateTime timeStamp;
    private Double value;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRENCY_PAIR_ID")
    private CurrencyPair currencyPair;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Order order;

    public CurrencyPairDataPoint() {
    }

    public CurrencyPairDataPoint(LocalDateTime timeStamp, Double value) {
        this.timeStamp = timeStamp;
        this.value = value;
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
        if(currencyPair != null){
            currencyPair.getCurrencyPairDataPoints().add(this);
        } else if (this.currencyPair != null){
            this.currencyPair.getCurrencyPairDataPoints().remove(this);
        }
        this.currencyPair = currencyPair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyPairDataPoint that = (CurrencyPairDataPoint) o;
        return Objects.equals(pointId, that.pointId) &&
                Objects.equals(timeStamp, that.timeStamp) &&
                Objects.equals(value, that.value) &&
                Objects.equals(currencyPair, that.currencyPair) &&
                Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointId, timeStamp, value, currencyPair, order);
    }

    @Override
    public String toString() {
        return "CurrencyPairDataPoint{" +
                "timeStamp=" + timeStamp +
                ", value=" + value +
                ", currencyPair=" + currencyPair.getCurrencyPairName() +
                '}';
    }
}
