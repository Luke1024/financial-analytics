package com.finance.data.domain.dto;

import java.time.LocalDateTime;

public class PairHistoryRequestDto {
    private String baseCurrencyName;
    private String currencyName;
    private boolean requestAllHistory; //override time settings
    private LocalDateTime start;
    private LocalDateTime stop;

    public PairHistoryRequestDto() {
    }

    public PairHistoryRequestDto(String baseCurrencyName, String currencyName, boolean requestAllHistory, LocalDateTime start, LocalDateTime stop) {
        this.baseCurrencyName = baseCurrencyName;
        this.currencyName = currencyName;
        this.requestAllHistory = requestAllHistory;
        this.start = start;
        this.stop = stop;
    }

    public String getBaseCurrencyName() {
        return baseCurrencyName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public boolean isRequestAllHistory() {
        return requestAllHistory;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getStop() {
        return stop;
    }
}
