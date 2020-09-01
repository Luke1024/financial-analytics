package com.finance.service.database.pairdatapointserviceutilities;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.PairDataRequest;
import com.finance.domain.dto.currencypair.PointTimeFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PairHistoryRetriever {

    private Logger logger = Logger.getLogger(PairHistoryRetriever.class.getName());

    @Autowired
    private CurrencyPairDataPointCache cache;

    public List<CurrencyPairDataPoint> getCurrencyPairHistory(PairDataRequest pairDataRequest){
        if( ! dtoCheck(pairDataRequest)){
            return Collections.emptyList();
        }

        CurrencyPair currencyPair = getCurrencyPair(pairDataRequest);
        if(currencyPair == null) {
            logger.log(Level.WARNING, "CurrencyPair " + pairDataRequest.getCurrencyName() + "not found.");
            return Collections.emptyList();
        }
        if(currencyPair.getCurrencyPairDataPoints() == null){
            logger.log(Level.WARNING, "CurrencyPair " + pairDataRequest.getCurrencyName() + " does not have dataPoint list.");
            return Collections.emptyList();
        }

        int dataPointSize = pairDataRequest.getNumberOfDataPoints();
        int dataPointsBeforeLast = pairDataRequest.getPointsBeforeLast();
        PointTimeFrame timeFrame = pairDataRequest.getPointTimeFrame();
        long currencyPairId = currencyPair.getId();

        return getCurrencyPairDataPoints(dataPointsBeforeLast, dataPointSize, timeFrame, currencyPair);
    }

    private boolean dtoCheck(PairDataRequest pairDataRequest){
        if(pairDataRequest.getCurrencyName() == null){
            logger.log(Level.WARNING, "CurrencyName in PairDataRequest is null.");
            return false;
        }
        if(pairDataRequest.getNumberOfDataPoints() == 0){
            logger.log(Level.WARNING, "NumberOfDataPoints in PairDataRequest is 0.");
        }
        if(pairDataRequest.getPointTimeFrame() == null){
            logger.log(Level.WARNING, "PointTimeFrame in PairDataRequest is 0.");
        }
        return true;
    }

    private CurrencyPair getCurrencyPair(PairDataRequest pairDataRequest){
        String currencyName = pairDataRequest.getCurrencyName();
        Optional<CurrencyPair> currencyPair = cache.findByCurrencyName(currencyName);
        if(currencyPair.isPresent()){
            return currencyPair.get();
        } else {
            return null;
        }
    }

    private List<CurrencyPairDataPoint> getCurrencyPairDataPoints(int pointsBeforeLast, int dataPointSize,
                                                                  PointTimeFrame timeFrame, CurrencyPair currencyPair) {
        int pointMultiplier = howMuchPointsGivenTimeFrameFromH1Dataset(timeFrame);
        int dataPointSizeMultiplied = computeRequiredDataPointsWithMultiplier(pointMultiplier, dataPointSize);
        int pointsBeforeLastMultiplied = computeRequiredPointsBeforeLastMultiplied(pointMultiplier, dataPointSize);

        //skipping empty points is automatic
        List<CurrencyPairDataPoint> dataPointList = currencyPair.getCurrencyPairDataPoints();

        int leftIndex = computeLeftIndex(dataPointSizeMultiplied, pointsBeforeLastMultiplied, dataPointList.size());
        int rightIndex = computeRightIndex(pointsBeforeLastMultiplied, dataPointList.size());

        return getRequiredPoints(dataPointList, pointMultiplier, leftIndex, rightIndex);
    }

    private List<CurrencyPairDataPoint> getRequiredPoints(List<CurrencyPairDataPoint> dataPointList, int pointMultiplier,
                                                          int leftIndex, int rightIndex) {
        List<CurrencyPairDataPoint> listCrop = dataPointList.subList(leftIndex, rightIndex);
        return dataPointsSkipper(listCrop, pointMultiplier);
    }

    private List<CurrencyPairDataPoint> dataPointsSkipper(List<CurrencyPairDataPoint> listCrop, int pointMultiplier) {
        List<CurrencyPairDataPoint> listWithDataPointSkipped = new ArrayList<>();
        for(int i=0; i<listCrop.size(); i += pointMultiplier) {
            listWithDataPointSkipped.add(listCrop.get(i));
        }
        return listWithDataPointSkipped;
    }

    private int computeLeftIndex(int dataPointSizeMultiplied, int pointsBeforeLastMultiplied, int listSize) {
        int leftIndex = listSize - dataPointSizeMultiplied + pointsBeforeLastMultiplied ;
        if(leftIndex < 0){
            return 0;
        } else {
            return leftIndex;
        }
    }

    private int computeRightIndex(int pointsBeforeLastMultiplied, int listSize) {
        int rightIndex = listSize - pointsBeforeLastMultiplied;
        if(rightIndex < 0){
            return 0;
        } else {
            return rightIndex;
        }
    }

    private int computeRequiredDataPointsWithMultiplier(int pointMultiplier, int dataPointSize){
        return dataPointSize * pointMultiplier;
    }

    private int computeRequiredPointsBeforeLastMultiplied(int pointMultiplier, int requiredPointsBeforeLast){
        return requiredPointsBeforeLast * pointMultiplier;
    }

    private int howMuchPointsGivenTimeFrameFromH1Dataset(PointTimeFrame timeFrame){
        if(timeFrame == PointTimeFrame.H1) return 1;
        if(timeFrame == PointTimeFrame.H5) return 5;
        if(timeFrame == PointTimeFrame.D1) return 24;
        if(timeFrame == PointTimeFrame.W1) return 168;
        if(timeFrame == PointTimeFrame.M1) return 720;
        return 1;
    }
}
