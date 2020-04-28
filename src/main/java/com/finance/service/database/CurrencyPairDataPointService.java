package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.currencyPair.PairDataRequestDto;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import com.finance.service.database.pairDataPointServiceUtilities.PairHistoryRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyPairDataPointService {

    private static final ChronoUnit defaultChronoUnitUsedForTimeStampCalculation = ChronoUnit.HOURS;

    @Autowired
    private CurrencyPairHistoryPointRepository repository;

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    @Autowired
    private PairHistoryRetriever pairHistoryRetriever;

    public List<CurrencyPairDataPoint> getCurrencyPairHistory(PairDataRequestDto pairDataRequestDto){
        return pairHistoryRetriever.getCurrencyPairHistory(pairDataRequestDto);
    }

    public void addHistoryPoints(List<CurrencyPairDataPoint> currencyPairDataPoints){
        for(CurrencyPairDataPoint point : currencyPairDataPoints){
            saveDataPoint(point);
        }
    }

    public void saveDataPoint(CurrencyPairDataPoint point){
        CurrencyPair currencyPair;
        currencyPair = retrieveCurrency(point);
        if(currencyPair == null) {
            createCurrency(point);
            currencyPair = retrieveCurrency(point);
        }
        Optional<CurrencyPairDataPoint> dataPoint = repository.findPointByDate(
                point.getTimeStamp(), currencyPair.getId());

        if (dataPoint.isPresent()) {
            repository.deleteById(dataPoint.get().getPointId());
        }
        repository.save(new CurrencyPairDataPoint(
                point.getTimeStamp(), point.getValue(), currencyPair));
    }

    private CurrencyPair retrieveCurrency(CurrencyPairDataPoint point){
        CurrencyPair currencyPair = new CurrencyPair();
        if(point != null) {
            String currencyPairName = point.getCurrencyPair().getCurrencyPairName();
            Optional<CurrencyPair> optionalCurrencyPair = currencyPairRepository.findByCurrencyName(currencyPairName);
            if(optionalCurrencyPair.isPresent()){
                currencyPair = optionalCurrencyPair.get();
            }
        }
        return currencyPair;
    }

    private void createCurrency(CurrencyPairDataPoint point){
        if(point != null){
            CurrencyPair currencyPair = new CurrencyPair(point.getCurrencyPair().getCurrencyPairName());
            currencyPairRepository.save(currencyPair);
            System.out.println(currencyPair.getId());
        }
    }

    /*
    private CurrencyPair retrieveCurrency(CurrencyPairDataPoint point) {
        String currencyPairName = point.getCurrencyPair().getCurrencyPairName();
        Optional<CurrencyPair> retrievedCurrency = currencyPairRepository.findByCurrencyName(currencyPairName);
        if (retrievedCurrency.isPresent()) {
            return retrievedCurrency.get();
        } else {
            createCurrency(point);
            return retrieveCurrency(point);
        }
    }

    private void createCurrency(CurrencyPairDataPoint point){
        CurrencyPair createdCurrencyPair = new CurrencyPair(point.getCurrencyPair().getCurrencyPairName());
        currencyPairRepository.save(createdCurrencyPair);
    }

     */
}