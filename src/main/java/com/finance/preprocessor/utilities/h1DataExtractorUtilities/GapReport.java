package com.finance.preprocessor.utilities.h1DataExtractorUtilities;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class GapReport {
    private ChronoUnit timeUnitType;
    private int minimalNumberOfTimeUnitsQualifiedAsGap;
    private boolean gapDetected;
    private int numberOfGaps;
    private List<Gap> gapList;

    public GapReport(ChronoUnit timeUnitType, int minimalNumberOfTimeUnitsQualifiedAsGap,
                     boolean gapDetected, int numberOfGaps, List<Gap> gapList) {
        this.timeUnitType = timeUnitType;
        this.minimalNumberOfTimeUnitsQualifiedAsGap = minimalNumberOfTimeUnitsQualifiedAsGap;
        this.gapDetected = gapDetected;
        this.numberOfGaps = numberOfGaps;
        this.gapList = gapList;
    }

    public ChronoUnit getTimeUnitType() {
        return timeUnitType;
    }

    public int getMinimalNumberOfTimeUnitsQualifiedAsGap() {
        return minimalNumberOfTimeUnitsQualifiedAsGap;
    }

    public boolean isGapDetected() {
        return gapDetected;
    }

    public int getNumberOfGaps() {
        return numberOfGaps;
    }

    public List<Gap> getGapList() {
        return gapList;
    }
}
