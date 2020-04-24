package com.finance.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(
        name="CurrencyPair.findByCurrencyName",
        query="SELECT * FROM currency_pair WHERE currency_pair_name =:CURRENCY_NAME",
        resultClass = CurrencyPair.class
)

@Entity
public class CurrencyPair {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String currencyPairName;
    @OneToMany(targetEntity = CurrencyPairDataPoint.class,
            mappedBy = "currency",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @OrderBy
    private List<CurrencyPairDataPoint> currencyPairDataPoints;

    public CurrencyPair() {
    }

    public CurrencyPair(String currencyPairName) {
        this.currencyPairName = currencyPairName;
        this.currencyPairDataPoints = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getCurrencyPairName() {
        return currencyPairName;
    }

    public CurrencyPairDataPoint getLastPairHistoryPoint() {
        return currencyPairDataPoints.get(currencyPairDataPoints.size()-1);
    }

    public List<CurrencyPairDataPoint> getCurrencyPairDataPoints() {
        return currencyPairDataPoints;
    }
}