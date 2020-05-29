package com.finance.domain.dto;

import com.finance.domain.dto.currencyPair.PointTimeFrame;

import java.time.LocalDateTime;

public class PairDataRequest {
    private String currencyName;
    private int numberOfDataPoints;
    private PointTimeFrame pointTimeFrame;
    private boolean fromLastPoint;
    private LocalDateTime adoptedLastPoint;

    public PairDataRequest() {
    }

    public PairDataRequest(String currencyName, int numberOfDataPoints, PointTimeFrame pointTimeFrame) {
        this.currencyName = currencyName;
        this.numberOfDataPoints = numberOfDataPoints;
        this.pointTimeFrame = pointTimeFrame;
        this.fromLastPoint = true;
    }

    public PairDataRequest(String currencyName, int numberOfDataPoints, PointTimeFrame pointTimeFrame, LocalDateTime adoptedLastPoint) {
        this.currencyName = currencyName;
        this.numberOfDataPoints = numberOfDataPoints;
        this.pointTimeFrame = pointTimeFrame;
        this.fromLastPoint = false;
        this.adoptedLastPoint = adoptedLastPoint;
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

    public LocalDateTime getAdoptedLastPoint() {
        return adoptedLastPoint;
    }
}
