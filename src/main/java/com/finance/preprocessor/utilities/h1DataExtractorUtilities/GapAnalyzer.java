package com.finance.preprocessor.utilities.h1DataExtractorUtilities;

import com.finance.preprocessor.utilities.DataPoint;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GapAnalyzer {

    //default settings
    private ChronoUnit timeUnitType = ChronoUnit.HOURS;
    private int minimalNumberOfTimeUnitsQualifiedAsGap = 1;

    public GapReport analyzeGaps(List<Integer> indexes,
                                 List<DataPoint> dataPoints, ChronoUnit timeUnitType,
                                 int minimalNumberOfTimeUnitsQualifiedAsGap) {
        this.timeUnitType = timeUnitType;
        this.minimalNumberOfTimeUnitsQualifiedAsGap = minimalNumberOfTimeUnitsQualifiedAsGap;

        List<Gap> gaps = searchForGaps(indexes, dataPoints);
        boolean gapDetected = isThereAnyGap(gaps);

        return new GapReport(timeUnitType, minimalNumberOfTimeUnitsQualifiedAsGap,
                gapDetected, gaps.size(), gaps);
    }

    private List<Gap> searchForGaps(List<Integer> indexes, List<DataPoint> dataPoints) {
        List<Gap> gaps = new ArrayList<>();
        for (int i = 0; i < indexes.size() - 1; i++) {
            if (detectGap(indexes.get(i), indexes.get(i + 1), dataPoints)) {
                gaps.add(getGap(indexes.get(i), indexes.get(i + 1), dataPoints));
            }
        }
        return gaps;
    }

    private boolean detectGap(int start, int stop, List<DataPoint> dataPoints){
        Gap gap = measureGap(start, stop,dataPoints);
        if(gap.getGapTimeUnit() > 1) return true;
        else return false;
    }

    private Gap getGap(int start, int stop, List<DataPoint> dataPoints){
        return measureGap(start, stop,dataPoints);
    }

    private Gap measureGap(int start, int stop, List<DataPoint> dataPoints){
        LocalDateTime pointEnd = dataPoints.get(stop).getLocalDateTime();
        LocalDateTime pointStart = dataPoints.get(start).getLocalDateTime();
        long hours = pointStart.until(pointEnd, timeUnitType);
        return new Gap(start, stop, hours);
    }

    private boolean isThereAnyGap(List<Gap> gaps){
        int detectedGaps = 0;
        for(Gap gap : gaps){
            if(gap.getGapTimeUnit() > minimalNumberOfTimeUnitsQualifiedAsGap) detectedGaps += 1;
        }
        if(detectedGaps > 0) return true;
        else return false;
    }
}
