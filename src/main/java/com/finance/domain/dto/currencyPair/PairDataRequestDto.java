package com.finance.domain.dto.currencyPair;

public class PairDataRequestDto {
    private String currencyName;
    private int numberOfDataPoints;
    private String pointTimeFrame;
    private boolean fromLastPoint;
    private String adoptedLastPoint;

    public PairDataRequestDto(String currencyName, int numberOfDataPoints, String pointTimeFrame, boolean fromLastPoint, String adoptedLastPoint) {
        this.currencyName = currencyName;
        this.numberOfDataPoints = numberOfDataPoints;
        this.pointTimeFrame = pointTimeFrame;
        this.fromLastPoint = fromLastPoint;
        this.adoptedLastPoint = adoptedLastPoint;
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

    public boolean isFromLastPoint() {
        return fromLastPoint;
    }

    public String getAdoptedLastPoint() {
        return adoptedLastPoint;
    }
}
