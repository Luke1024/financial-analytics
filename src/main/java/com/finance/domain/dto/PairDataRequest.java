package com.finance.domain.dto;

import com.finance.domain.dto.currencyPair.PointTimeFrame;

import java.time.LocalDateTime;

public class PairDataRequest {
    private String currencyName;
    private int numberOfDataPoints;
    private PointTimeFrame pointTimeFrame;
    private boolean fromLastPoint;
    private LocalDateTime adoptedlastPoint;

    public PairDataRequest() {
    }

    public PairDataRequest(String currencyName, int numberOfDataPoints, PointTimeFrame pointTimeFrame) {
        this.currencyName = currencyName;
        this.numberOfDataPoints = numberOfDataPoints;
        this.pointTimeFrame = pointTimeFrame;
        this.fromLastPoint = true;
    }

    public PairDataRequest(String currencyName, int numberOfDataPoints, PointTimeFrame pointTimeFrame, LocalDateTime adoptedlastPoint) {
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
