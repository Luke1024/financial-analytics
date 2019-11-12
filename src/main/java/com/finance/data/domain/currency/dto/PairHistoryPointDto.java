package com.finance.data.domain.currency.dto;

import java.time.LocalDateTime;

public class PairHistoryPointDto {
    private LocalDateTime timeStamp;
    private Double value;

    public PairHistoryPointDto(LocalDateTime timeStamp, Double value) {
        this.timeStamp = timeStamp;
        this.value = value;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public Double getValue() {
        return value;
    }
}
