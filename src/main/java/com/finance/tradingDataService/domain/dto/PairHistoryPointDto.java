package com.finance.tradingDataService.domain.dto;

import java.time.LocalDateTime;

public class PairHistoryPointDto {
    private LocalDateTime time;
    private Double value;

    public PairHistoryPointDto(LocalDateTime time, Double value) {
        this.time = time;
        this.value = value;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Double getValue() {
        return value;
    }
}
