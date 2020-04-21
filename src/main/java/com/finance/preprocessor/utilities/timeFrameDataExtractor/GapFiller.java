package com.finance.preprocessor.utilities.timeFrameDataExtractor;

import com.finance.preprocessor.utilities.DataPoint;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GapFiller {
    public List<DataPoint> fill(List<DataPoint> dataPoints, ChronoUnit inputTimeFrame){
        List<DataPoint> dataPointsWithoutGaps = new ArrayList<>();

        for(int i=0; i<dataPoints.size()-1; i++){
            DataPoint dataPointStart = dataPoints.get(i);
            DataPoint dataPointStop = dataPoints.get(i+1);

            if(detectGap(dataPointStart, dataPointStop, inputTimeFrame)){
                dataPointsWithoutGaps.addAll(gapfilling(dataPointStart, dataPointStop, inputTimeFrame));
            } else {
                dataPointsWithoutGaps.add(dataPointsWithoutGaps.get(i));
            }
        }
        //adding last value
        dataPointsWithoutGaps.add(dataPoints.get(dataPoints.size()));
        return dataPointsWithoutGaps;
    }

    private boolean detectGap(DataPoint dataPointStart, DataPoint dataPointStop, ChronoUnit inputTimeFrame){
        LocalDateTime start = dataPointStart.getLocalDateTime();
        LocalDateTime stop = dataPointStop.getLocalDateTime();

        if(start.until(stop,inputTimeFrame)>1) return true;
        else return false;
    }

    private List<DataPoint> gapfilling(DataPoint dataPointStart, DataPoint dataPointStop, ChronoUnit inputTimeFrame){
        LocalDateTime start = dataPointStart.getLocalDateTime();
        LocalDateTime stop = dataPointStop.getLocalDateTime();

        double valueStart = dataPointStart.getValue();
        double valueStop = dataPointStop.getValue();

        long gapSize = start.until(stop, inputTimeFrame);
        double changeInValue = valueStop - valueStart;

        double valueChangeInGapStep = changeInValue/gapSize;

        List<DataPoint> gapFillerDataPoints = new ArrayList<>();
        for(int i=1; i<gapSize; i++){
            gapFillerDataPoints.add(new DataPoint(start.plus(i, inputTimeFrame),i*valueChangeInGapStep));
        }
        return gapFillerDataPoints;
    }
}
