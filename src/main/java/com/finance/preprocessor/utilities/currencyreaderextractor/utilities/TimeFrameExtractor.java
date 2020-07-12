package com.finance.preprocessor.utilities.currencyreaderextractor.utilities;

import com.finance.preprocessor.utilities.DataPoint;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeFrameExtractor {

    private List<DataPoint> extractedDataPoints;
    private List<DataPoint> dataPointsListToExtract;
    private ChronoUnit outputTimeFrame;
    private ChronoUnit inputTimeFrame;
    private LocalDateTime searchedTimeStamp;
    private DataPoint substituteDataPoint;
    private long searchedDistance;
    private long substituteDataPointDistance;

    public List<DataPoint> extract(List<DataPoint> dataPointList,
                                   ChronoUnit outputTimeFrame,
                                   ChronoUnit inputTimeFrame,
                                   int searchSubstitutePointInDistanceOfInputTimeFrames){

        this.outputTimeFrame = outputTimeFrame;
        this.inputTimeFrame = inputTimeFrame;
        this.extractedDataPoints = new ArrayList<>();
        this.dataPointsListToExtract = dataPointList;
        this.searchedTimeStamp = null;
        this.substituteDataPoint = null;
        this.searchedDistance = searchSubstitutePointInDistanceOfInputTimeFrames;
        this.substituteDataPointDistance = searchSubstitutePointInDistanceOfInputTimeFrames + 1;

        for(int i=0; i<dataPointList.size()-1; i++){
            extractPoint(dataPointList.get(i));
        }
        return this.extractedDataPoints;
    }

    private void extractPoint(DataPoint dataPoint){
        if(dataPoint != null) {
            if (dataPoint.getLocalDateTime() != null) {
                continueExtraction(dataPoint);
            }
        }
    }

    private void continueExtraction(DataPoint dataPoint){
        loadSearchedTimeStamp(dataPoint);
        loadDataPointIfExactMatch(dataPoint);
        loadSubstituteDataPoint(dataPoint);
    }

    private void loadSearchedTimeStamp(DataPoint dataPoint){
        if(this.searchedTimeStamp==null){
            this.searchedTimeStamp=computeNearestExpectedTimeStamp(dataPoint.getLocalDateTime());
        }
    }

    private LocalDateTime computeNearestExpectedTimeStamp(LocalDateTime dataPointTimestamp){
        if(this.outputTimeFrame == ChronoUnit.DAYS){
            return expected(dataPointTimestamp.withHour(0).withMinute(0).withSecond(0).withNano(0));
        }
        if(this.outputTimeFrame == ChronoUnit.HOURS){
            return expected(dataPointTimestamp.withMinute(0).withSecond(0).withNano(0));
        }
        //default behavior
        return expected(dataPointTimestamp.withMinute(0).withSecond(0).withNano(0));
    }

    private LocalDateTime expected(LocalDateTime dateTime){
        return dateTime.plus(1, this.outputTimeFrame);
    }

    private void loadDataPointIfExactMatch(DataPoint dataPoint){
        if(this.searchedTimeStamp == dataPoint.getLocalDateTime()){
            this.extractedDataPoints.add(dataPoint);
            resetSearcher();
        }
    }

    private void loadSubstituteDataPoint(DataPoint dataPoint){
        long distance;
        if(this.searchedTimeStamp != null){
            distance = measureDistance(dataPoint.getLocalDateTime());
            if(distance <= this.substituteDataPointDistance){
                this.substituteDataPointDistance = distance;
                this.substituteDataPoint = dataPoint;
            } else {
                if(this.substituteDataPoint != null) {
                    this.extractedDataPoints.add(this.substituteDataPoint);
                    resetSearcher();
                }
            }
        }
    }

    private long measureDistance(LocalDateTime timeStamp){
        Duration duration = Duration.between(this.searchedTimeStamp, timeStamp);
        if(this.inputTimeFrame == ChronoUnit.HOURS){
            return duration.abs().toHours();
        }
        if(this.inputTimeFrame == ChronoUnit.MINUTES){
            return duration.abs().toMinutes();
        }
        //default behavior
        return duration.abs().toMinutes();
    }

    private void resetSearcher(){
        this.searchedTimeStamp = null;
        this.substituteDataPoint = null;
        this.substituteDataPointDistance = this.searchedDistance + 1;
    }
}
