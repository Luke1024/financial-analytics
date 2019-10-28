package com.finance.tradingDataService.domain.dto;

import java.time.LocalDateTime;

public class CurrencyHistoryPoint {
    private LocalDateTime localDateTime;
    private Double value;

    public CurrencyHistoryPoint(LocalDateTime localDateTime, Double value) {
        this.localDateTime = localDateTime;
        this.value = value;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Double getValue() {
        return value;
    }
}
