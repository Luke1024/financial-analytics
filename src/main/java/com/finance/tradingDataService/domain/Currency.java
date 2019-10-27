package com.finance.tradingDataService.domain;

import javax.persistence.*;
import java.util.List;

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
            fetch = FetchType.LAZY
    )
    private List<CurrencyHistoryPoint> currencyHistoryPoints;

    public Currency(String base, String currencyName, List<CurrencyHistoryPoint> currencyHistoryPoints) {
        this.base = base;
        this.currencyName = currencyName;
        this.currencyHistoryPoints = currencyHistoryPoints;
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
