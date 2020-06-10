package com.finance.domain.dto.currencyPair;

public class PairDataRequestDto {
    private String currencyName;
    private int numberOfDataPoints;
    private String pointTimeFrame;
    private int pointsBeforeLast;

    public PairDataRequestDto(String currencyName, int numberOfDataPoints, String pointTimeFrame, int pointsBeforeLast) {
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

    public String getPointTimeFrame() {
        return pointTimeFrame;
    }

    public int getPointsBeforeLast() {
        return pointsBeforeLast;
    }
}
