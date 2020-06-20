package com.finance.preprocessor.utilities.currencyreaderextractor.utilities;

import com.finance.preprocessor.utilities.DataPoint;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GapFiller {

    private Logger logger = Logger.getLogger(GapFiller.class.getName());

    private ChronoUnit inputTimeFrame = ChronoUnit.HOURS;

    public List<DataPoint> fill(List<DataPoint> dataPoints, ChronoUnit inputTimeFrame){
        if(dataPoints == null) {
            logger.log(Level.WARNING, "DataPoints list is null.");
            return Collections.emptyList();
        }
        if(dataPoints.isEmpty()) {
            logger.log(Level.WARNING, "DataPoints list is empty.");
            return Collections.emptyList();
        }

        setInputTimeFrame(inputTimeFrame);
        List<DataPoint> dataPointsWithoutNulls = skipNullInDataPoints(dataPoints);

        List<DataPoint> dataPointsWithoutGaps = new ArrayList<>();
        for(int i=0; i<dataPointsWithoutNulls.size()-1; i++) {
            DataPoint start = dataPointsWithoutNulls.get(i);
            DataPoint stop = dataPointsWithoutNulls.get(i + 1);
            if (detectGap(start, stop)) {
                dataPointsWithoutGaps.addAll(fillSingleGap(start, stop));
            }
        }
        return skipPointWithTheSameTimeStamp(dataPointsWithoutGaps);
    }

    private boolean isNull(List<DataPoint> dataPoints){
        if(dataPoints == null){
            logger.log(Level.SEVERE, "DataPoints list is null.");
            return true;
        } else {
            return false;
        }
    }

    private void setInputTimeFrame(ChronoUnit inputTimeFrame){
        if(inputTimeFrame == null){
            logger.log(Level.WARNING, "ChronoUnit not specified, using default value.");
        } else {
            this.inputTimeFrame = inputTimeFrame;
        }
    }

    private List<DataPoint> skipNullInDataPoints(List<DataPoint> dataPoints) {
        List<DataPoint> dataPointListWithoutNulls = new ArrayList<>();

        for (DataPoint point : dataPoints) {
            if (point != null && point.getLocalDateTime() != null) {
                dataPointListWithoutNulls.add(point);
            }
        }
        return dataPointListWithoutNulls;
    }

    private boolean detectGap(DataPoint dataPointStart, DataPoint dataPointStop){
        LocalDateTime start = dataPointStart.getLocalDateTime();
        LocalDateTime stop = dataPointStop.getLocalDateTime();

        return start.until(stop,this.inputTimeFrame)>1;
    }

    private List<DataPoint> fillSingleGap(DataPoint dataPointStart, DataPoint dataPointStop){

        LocalDateTime dateStart = dataPointStart.getLocalDateTime();
        LocalDateTime dateStop = dataPointStop.getLocalDateTime();

        double valueStart = dataPointStart.getValue();
        double valueStop = dataPointStop.getValue();

        long gapSize = dateStart.until(dateStop, inputTimeFrame);
        double changeInValue = valueStop - valueStart;

        double valueChangeInGapStep = changeInValue/gapSize;

        List<DataPoint> gapFilledDataPoints = new ArrayList<>();

        gapFilledDataPoints.add(dataPointStart);
        for(int i=1; i<gapSize; i++){
            gapFilledDataPoints.add(new DataPoint(dateStart.plus(i, inputTimeFrame),valueStart + i * valueChangeInGapStep));
        }
        gapFilledDataPoints.add(dataPointStop);

        return gapFilledDataPoints;
    }

    private List<DataPoint> skipPointWithTheSameTimeStamp(List<DataPoint> points){
        List<DataPoint> dataPoints = new ArrayList<>();
        for (int i = 1; i < points.size(); i++) {
            if (points.get(i - 1).getLocalDateTime() != points.get(i).getLocalDateTime()) {
                dataPoints.add(points.get(i));
            }
        }
        return dataPoints;
    }
}
