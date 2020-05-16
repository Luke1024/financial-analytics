package com.finance.domain;

import com.finance.service.database.communicationObjects.DatabaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NamedNativeQuery(
        name="CurrencyPair.findByCurrencyName",
        query="SELECT * FROM currency_pair WHERE currency_pair_name =:CURRENCY_NAME",
        resultClass = CurrencyPair.class
)

@Entity
public class CurrencyPair implements DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String currencyPairName;
    @OneToMany(targetEntity = CurrencyPairDataPoint.class,
            mappedBy = "currencyPair",
            cascade = {CascadeType.ALL},//, CascadeType.REFRESH, CascadeType.REMOVE},
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

    public CurrencyPair(Long id, String currencyPairName) {
        this.id = id;
        this.currencyPairName = currencyPairName;
        this.currencyPairDataPoints = new ArrayList<>();
    }

    public CurrencyPair(String currencyPairName, List<CurrencyPairDataPoint> pairDataPoints) {
        this.id = id;
        this.currencyPairName = currencyPairName;
        this.currencyPairDataPoints = pairDataPoints;
    }

    public Long getId() {
        return id;
    }

    public String getCurrencyPairName() {
        return currencyPairName;
    }

    public List<CurrencyPairDataPoint> getCurrencyPairDataPoints() {
        return currencyPairDataPoints;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCurrencyPairName(String currencyPairName) {
        this.currencyPairName = currencyPairName;
    }

    public void setCurrencyPairDataPoints(List<CurrencyPairDataPoint> currencyPairDataPoints) {
        this.currencyPairDataPoints = currencyPairDataPoints;
    }

    public void addDataPoint(CurrencyPairDataPoint pairDataPoint){
        pairDataPoint.setCurrencyPair(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyPair that = (CurrencyPair) o;
        return id.equals(that.id) &&
                currencyPairName.equals(that.currencyPairName) &&
                currencyPairDataPoints.equals(that.currencyPairDataPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currencyPairName, currencyPairDataPoints);
    }

    @Override
    public String toString() {
        return "CurrencyPair{" +
                "id=" + id +
                ", currencyPairName='" + currencyPairName + '\'' +
                '}';
    }
}