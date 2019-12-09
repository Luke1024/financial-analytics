package com.finance.data.domain.currency;

import javax.persistence.*;
import java.util.List;
/*
@NamedNativeQuery(
        name="Currency.retrieveByBaseAndName",
        query="SELECT * FROM currency WHERE base =:BASE AND currency_name =:CURRENCY_NAME",
        resultClass = CurrencyPair.class
)
*/

@Entity
public class CurrencyPair {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String currencyPairName;
    @OneToMany(targetEntity = CurrencyPairHistoryPoint.class,
            mappedBy = "currency",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @OrderBy
    private List<CurrencyPairHistoryPoint> currencyPairHistoryPoints;

    public CurrencyPair() {
    }

    public CurrencyPair(String currencyPairName) {
        this.currencyPairName = currencyPairName;
    }

    public void setCurrencyPairHistoryPoints(List<CurrencyPairHistoryPoint> currencyPairHistoryPoints) {
        this.currencyPairHistoryPoints = currencyPairHistoryPoints;
    }

    public Long getId() {
        return id;
    }

    public String getCurrencyPairName() {
        return currencyPairName;
    }

    public List<CurrencyPairHistoryPoint> getCurrencyPairHistoryPoints() {
        return currencyPairHistoryPoints;
    }
}
