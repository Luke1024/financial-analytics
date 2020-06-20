package com.finance.preprocessor.utilities.currencyreaderextractor.utilities;

import com.finance.preprocessor.utilities.DataPoint;
import com.finance.preprocessor.utilities.DataPointPack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeFrameExtractor {

    @Autowired
    private GapFiller gapFiller;

    public List<DataPoint> extract(List<DataPoint> dataPointList,
                                   ChronoUnit outputTimeFrame, ChronoUnit inputTimeFrame){

        List<DataPoint> dataPointsWithoutGaps = gapFiller.fill(dataPointList, inputTimeFrame);

        return getRequiredTimeFrame(dataPointsWithoutGaps, outputTimeFrame);
    }

    private List<DataPoint> packsSerialized(List<DataPointPack> dataPointPacks){
        List<DataPoint> packsSerialized = new ArrayList<>();

        for(DataPointPack dataPointPack : dataPointPacks){
            packsSerialized.addAll(dataPointPack.getDataPointList());
        }
        return packsSerialized;
    }

    private List<DataPoint> getRequiredTimeFrame(List<DataPoint> dataPoints, ChronoUnit outputTimeFrame){
        List<DataPoint> dataPointsInRequiredTimeFrame = new ArrayList<>();
        for(DataPoint dataPoint : dataPoints){
            LocalDateTime pointTime = dataPoint.getLocalDateTime();
            if(checkIfRequiredChronoUnitZero(pointTime, outputTimeFrame)) {
                dataPointsInRequiredTimeFrame.add(dataPoint);
            }
        }
        return dataPointsInRequiredTimeFrame;
    }

    private boolean checkIfRequiredChronoUnitZero(LocalDateTime localDateTime, ChronoUnit outputTimeFrame){
        if(outputTimeFrame == ChronoUnit.DAYS && localDateTime.getHour()==0){
            return true;
        }
        if(outputTimeFrame == ChronoUnit.HOURS && localDateTime.getMinute()==0){
            return true;
        }
        if(outputTimeFrame == ChronoUnit.MINUTES && localDateTime.getSecond()==0){
            return true;
        }
        return false;
    }
}
