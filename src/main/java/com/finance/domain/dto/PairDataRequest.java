package com.finance.domain.dto;

import com.finance.domain.dto.currencypair.PointTimeFrame;

public class PairDataRequest {
    private String currencyName;
    private int numberOfDataPoints;
    private PointTimeFrame pointTimeFrame;
    private int pointsBeforeLast;

    public PairDataRequest() {
    }

    public PairDataRequest(String currencyName, int numberOfDataPoints,
                           PointTimeFrame pointTimeFrame, int pointsBeforeLast) {
        this.currencyName = currencyName;
        this.numberOfDataPoints = numberOfDataPoints;
        this.pointTimeFrame = pointTimeFrame;
        this.pointsBeforeLast = pointsBeforeLast;
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

    public int getPointsBeforeLast() {
        return pointsBeforeLast;
    }

    @Override
    public String toString() {
        return "PairDataRequest{" +
                "currencyName='" + currencyName + '\'' +
                ", numberOfDataPoints=" + numberOfDataPoints +
                ", pointTimeFrame=" + pointTimeFrame +
                ", pointsBeforeLast=" + pointsBeforeLast +
                '}';
    }
}
