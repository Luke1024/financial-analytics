package com.finance.data.domain.currency;

import javax.persistence.*;
import java.util.List;

@NamedNativeQuery(
        name="Currency.retrieveByBaseAndName",
        query="SELECT * FROM currency WHERE base =:BASE AND currency_name =:CURRENCY_NAME",
        resultClass = Currency.class
)

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String currencyName;
    private String base;
    @OneToMany(targetEntity = CurrencyHistoryPoint.class,
            mappedBy = "currency",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @OrderBy
    private List<CurrencyHistoryPoint> currencyHistoryPoints;

    public Currency() {
    }

    public Currency(String base, String currencyName) {
        this.base = base;
        this.currencyName = currencyName;
    }

    public void setCurrencyHistoryPoints(List<CurrencyHistoryPoint> currencyHistoryPoints) {
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
