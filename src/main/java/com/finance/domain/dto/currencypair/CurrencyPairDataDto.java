package com.finance.domain.dto.currencypair;

import java.time.LocalDateTime;
import java.util.List;

public class CurrencyPairDataDto {
    private String currencyName;
    private LocalDateTime lastRetrieved;
    private List<DataPointDto> dataPoints;

    public CurrencyPairDataDto() {
    }

    public CurrencyPairDataDto(String currencyName, LocalDateTime lastRetrieved, List<DataPointDto> dataPoints) {
        this.currencyName = currencyName;
        this.lastRetrieved = lastRetrieved;
        this.dataPoints = dataPoints;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public LocalDateTime getLastRetrieved() {
        return lastRetrieved;
    }

    public List<DataPointDto> getDataPoints() {
        return dataPoints;
    }
}
