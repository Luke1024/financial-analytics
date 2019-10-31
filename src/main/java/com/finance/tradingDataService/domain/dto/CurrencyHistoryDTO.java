package com.finance.tradingDataService.domain.dto;

import com.finance.tradingDataService.domain.CurrencyHistoryPoint;

import java.util.List;

public class CurrencyHistoryDTO {
    private String baseCurrency;
    private String currency;
    private List<CurrencyHistoryPoint> currencyHistory;

    public CurrencyHistoryDTO(String baseCurrency, String currency, List<CurrencyHistoryPoint> currencyHistory) {
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.currencyHistory = currencyHistory;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public List<CurrencyHistoryPoint> getCurrencyHistory() {
        return currencyHistory;
    }
}
