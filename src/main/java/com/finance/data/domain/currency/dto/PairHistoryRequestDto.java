package com.finance.data.domain.currency.dto;

import java.time.LocalDateTime;

public class PairHistoryRequestDto {
    private String pairName;
    private boolean requestAllHistory; //override time settings
    private LocalDateTime start;
    private LocalDateTime stop;

    public PairHistoryRequestDto() {
    }

    public PairHistoryRequestDto(String pairName, boolean requestAllHistory, LocalDateTime start, LocalDateTime stop) {
        this.pairName = pairName;
        this.requestAllHistory = requestAllHistory;
        this.start = start;
        this.stop = stop;
    }

    public String getPairName() {
        return pairName;
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
