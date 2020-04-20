package com.finance.preprocessor.utilities.h1DataExtractorUtilities;

public class Gap {
    private int startIndex;
    private int stopIndex;
    private long gapTimeUnit;

    public Gap(int startIndex, int stopIndex, long gapTimeUnit) {
        this.startIndex = startIndex;
        this.stopIndex = stopIndex;
        this.gapTimeUnit = gapTimeUnit;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getStopIndex() {
        return stopIndex;
    }

    public long getGapTimeUnit() {
        return gapTimeUnit;
    }
}
