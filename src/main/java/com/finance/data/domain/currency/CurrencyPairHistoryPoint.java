package com.finance.data.domain.currency;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedNativeQuery(
        name="CurrencyHistoryPoint.retrieveByTimeRangeAndCurrencyId",
        query="SELECT * FROM trading_data_microservice.currency_history_point" +
                " WHERE time_stamp => :TIME_STAMP_START AND " +
                "time_stamp <= :TIME_STAMP_STOP AND currency_id = :CURRENCY_ID;",
        resultClass = CurrencyPair.class
)

@Entity
public class CurrencyPairHistoryPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pointId;
    private LocalDateTime timeStamp;
    private Double value;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRENCY_PAIR_ID")
    private CurrencyPair currency;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Order order;

    public CurrencyPairHistoryPoint() {
    }

    public CurrencyPairHistoryPoint(LocalDateTime timeStamp, Double value, CurrencyPair currency) {
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

    public CurrencyPair getCurrency() {
        return currency;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
