package com.finance.mapper;

import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.PairDataRequest;
import com.finance.domain.dto.currencypair.DataPointDto;
import com.finance.domain.dto.currencypair.PairDataRequestDto;
import com.finance.domain.dto.currencypair.PointTimeFrame;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyPairDataPointMapper {

    public List<DataPointDto> mapToDataPoints(List<CurrencyPairDataPoint> currencyPairDataPoints) {
        List<DataPointDto> dataPointDtos = new ArrayList<>();
        for(CurrencyPairDataPoint point : currencyPairDataPoints){
            if(point == null){
                dataPointDtos.add(null);
            } else {
                dataPointDtos.add(new DataPointDto(point.getTimeStamp(), point.getValue()));
            }
        }
        return dataPointDtos;
    }

    public PairDataRequest mapToPairDataRequest(PairDataRequestDto pairDataRequestDto){
        try {
            String currencyName = pairDataRequestDto.getCurrencyName();
            int numberOfDataPoints = pairDataRequestDto.getNumberOfDataPoints();
            PointTimeFrame pointTimeFrame = mapPointTimeFrame(pairDataRequestDto.getPointTimeFrame());
            int pointsBeforeLast = pairDataRequestDto.getPointsBeforeLast();

            return new PairDataRequest(currencyName, numberOfDataPoints, pointTimeFrame, pointsBeforeLast);
        } catch (Exception e){
            return null;
        }
    }

    public PointTimeFrame mapPointTimeFrame(String pointTimeFrame){
        PointTimeFrame timeFrame = PointTimeFrame.H1;
        if(pointTimeFrame.equals("H1")) return PointTimeFrame.H1;
        if(pointTimeFrame.equals("H5")) return PointTimeFrame.H5;
        if(pointTimeFrame.equals("D1")) return PointTimeFrame.D1;
        if(pointTimeFrame.equals("W1")) return PointTimeFrame.W1;
        if(pointTimeFrame.equals("M1")) return PointTimeFrame.M1;
        return timeFrame;
    }
}
