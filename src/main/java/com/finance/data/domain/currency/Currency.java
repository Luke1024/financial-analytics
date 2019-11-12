package com.finance.data.domain.currency;

import javax.persistence.*;
import java.util.List;

@NamedNativeQuery(
        name="Currency.retrieveByName",
        query="SELECT * FROM currency WHERE currency_name = :CURRENCY_NAME",
        resultClass = Currency.class
)

@Entity
public class Currency {
    @Id
    @GeneratedValue
    private Long id;
    private String currencyName;
    private String base;
    @OneToMany(targetEntity = CurrencyHistoryPoint.class,
            mappedBy = "currency",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<CurrencyHistoryPoint> currencyHistoryPoints;

    public Currency() {
    }

    public Currency(String base, String currencyName, List<CurrencyHistoryPoint> currencyHistoryPoints) {
        this.base = base;
        this.currencyName = currencyName;
        this.currencyHistoryPoints = currencyHistoryPoints;
    }

    public Long getId() {
        return id;
    }

    public String getBase() {
        return base;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public List<CurrencyHistoryPoint> getCurrencyHistoryPoints() {
        return currencyHistoryPoints;
    }
}
