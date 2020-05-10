package com.finance.domain.dto.currencyPair;

import java.time.LocalDateTime;

public class PairDataRequestDto {
    private String currencyName;
    private int numberOfDataPoints;
    private PointTimeFrame pointTimeFrame;
    private boolean fromLastPoint;
    private LocalDateTime adoptedlastPoint;

    public PairDataRequestDto() {
    }

    public PairDataRequestDto(String currencyName, int numberOfDataPoints, PointTimeFrame pointTimeFrame) {
        this.currencyName = currencyName;
        this.numberOfDataPoints = numberOfDataPoints;
        this.pointTimeFrame = pointTimeFrame;
        this.fromLastPoint = true;
    }

    public PairDataRequestDto(String currencyName, int numberOfDataPoints, PointTimeFrame pointTimeFrame, LocalDateTime adoptedlastPoint) {
        this.currencyName = currencyName;
        this.numberOfDataPoints = numberOfDataPoints;
        this.pointTimeFrame = pointTimeFrame;
        this.fromLastPoint = false;
        this.adoptedlastPoint = adoptedlastPoint;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public int getNumberOfDataPoints() {
        return numberOfDataPoints;
    }

    public PointTimeFrame getPointTimeFrame() {
        return pointTimeFrame;
    }

    public boolean isFromLastPoint() {
        return fromLastPoint;
    }

    public LocalDateTime getAdoptedlastPoint() {
        return adoptedlastPoint;
    }
}
