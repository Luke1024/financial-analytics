package com.finance.service.database.pairdatapointserviceutilities;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class DataPointAdder {

    @Autowired
    private CurrencyPairHistoryPointRepository repository;

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    private Logger logger = Logger.getLogger(DataPointAdder.class.getName());

    private boolean overwrite;

    public void addPoint(List<CurrencyPairDataPoint> currencyPairDataPoints, String currencyPairName, boolean overwrite){
        if(currencyPairDataPoints == null) return;
        if(currencyPairName == null) return;

        this.overwrite = overwrite;
        saveDataPoints(currencyPairDataPoints, currencyPairName);
    }

    private void saveDataPoints(List<CurrencyPairDataPoint> points, String currencyPairName){
        List<CurrencyPairDataPoint> filteredPoints = filterPoints(points);
        List<LocalDateTime> alreadyUsedTimeStamps = getTimeStampsAlreadyUsedAndDeleteIfNecessary(filteredPoints, currencyPairName);
        List<CurrencyPairDataPoint> pointsToSave = choosePointsToSave(filteredPoints, alreadyUsedTimeStamps);

        Optional<CurrencyPair> optionalPair = getCurrencyPair(currencyPairName);
        if (optionalPair.isPresent()) {
            addDataPointsToCurrencyPair(pointsToSave, optionalPair.get());
        } else {
            logger.log(Level.WARNING, "CurrencyPair " + currencyPairName + " not found.");
        }
    }

    private List<CurrencyPairDataPoint> filterPoints(List<CurrencyPairDataPoint> points){
        List<CurrencyPairDataPoint> filteredPoints = new ArrayList<>();
        for(CurrencyPairDataPoint point : points){
            if(point != null){
                if(point.getTimeStamp() != null){
                    filteredPoints.add(point);
                }
            }
        }
        return filteredPoints;
    }

    private List<LocalDateTime> getTimeStampsAlreadyUsedAndDeleteIfNecessary(List<CurrencyPairDataPoint> points, String currencyPairName){
        List<LocalDateTime> alreadyUsedTimeStamps = new ArrayList<>();
        Optional<CurrencyPair> currencyPair = getCurrencyPair(currencyPairName);
        if(currencyPair.isPresent()) {
            for (CurrencyPairDataPoint point : points) {
                Optional<CurrencyPairDataPoint> dataPoint = repository.findPointByDate(point.getTimeStamp(),
                        currencyPair.get().getId());
                if(dataPoint.isPresent()) {
                    deleteDataPoint(dataPoint.get().getPointId());
                    alreadyUsedTimeStamps.add(dataPoint.get().getTimeStamp());
                }
            }
        } else {
            logger.log(Level.WARNING, "CurrencyPair not found.");
        }
        return alreadyUsedTimeStamps;
    }

    private void deleteDataPoint(Long pointId) {
        if(this.overwrite) {
            repository.deleteById(pointId);
        }
    }

    private Optional<CurrencyPair> getCurrencyPair(String currencyPairName) {
        return currencyPairRepository.findByCurrencyName(currencyPairName);
    }

    private List<CurrencyPairDataPoint> choosePointsToSave(List<CurrencyPairDataPoint> points,
                                                           List<LocalDateTime> alreadyUsedTimeStamps) {

        List<CurrencyPairDataPoint> pointsToSave = new ArrayList<>();
        if(this.overwrite){
            return points;
        } else {
            for(CurrencyPairDataPoint point : points) {
                List<LocalDateTime> theSameTimeStamps = alreadyUsedTimeStamps.stream()
                        .filter(stamp -> point.getTimeStamp() == stamp).collect(Collectors.toList());
                if(theSameTimeStamps.isEmpty()){
                    pointsToSave.add(point);
                }
            }
        }
        return pointsToSave;
    }


    private void addDataPointsToCurrencyPair(List<CurrencyPairDataPoint> points, CurrencyPair currencyPair){
        currencyPair.addDataPoint(points);
        for(CurrencyPairDataPoint point : points) {
            point.setCurrencyPair(currencyPair);
        }
        currencyPairRepository.save(currencyPair);
    }
}