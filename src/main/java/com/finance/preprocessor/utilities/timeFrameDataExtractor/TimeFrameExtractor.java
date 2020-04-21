package com.finance.preprocessor.utilities.timeFrameDataExtractor;

import com.finance.preprocessor.utilities.DataPoint;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class TimeFrameExtractor {

    private GapFiller gapFiller = new GapFiller();

    public List<DataPoint> extract(List<DataPoint> dataPointList,
                                   ChronoUnit outputTimeFrame, ChronoUnit inputTimeFrame){

        List<DataPoint> dataPointsWithoutGaps = gapFiller.fill(dataPointList, inputTimeFrame);

        return getRequiredTimeFrame(dataPointsWithoutGaps, outputTimeFrame);
    }

    private List<DataPoint> getRequiredTimeFrame(List<DataPoint> dataPoints, ChronoUnit outputTimeFrame){
        List<DataPoint> dataPointsInRequiredTimeFrame = new ArrayList<>();
        for(DataPoint dataPoint : dataPoints){
            LocalDateTime pointTime = dataPoint.getLocalDateTime();
            if(checkIfRequiredChronoUnitZero(pointTime, outputTimeFrame)) dataPointsInRequiredTimeFrame.add(dataPoint);
        }
        return dataPointsInRequiredTimeFrame;
    }

    private boolean checkIfRequiredChronoUnitZero(LocalDateTime localDateTime, ChronoUnit outputTimeFrame){
        if(outputTimeFrame == ChronoUnit.DAYS){
            if(localDateTime.getHour()==0){
                return true;
            }
        }
        if(outputTimeFrame == ChronoUnit.HOURS){
            if(localDateTime.getMinute()==0){
                return true;
            }
        }
        if(outputTimeFrame == ChronoUnit.MINUTES){
            if(localDateTime.getSecond()==0){
                return true;
            }
        }
        return false;
    }
}
