package com.finance.data.service.currency;

import com.finance.data.domain.currency.CurrencyPair;
import com.finance.data.domain.currency.CurrencyPairHistoryPoint;
import com.finance.data.domain.currency.dto.PairHistoryRequestDto;
import com.finance.data.repository.currency.CurrencyHistoryPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyPairService {

    @Autowired
    private CurrencyHistoryPointRepository currencyHistoryPointRepository;

    public List<CurrencyPairHistoryPoint> getCurrencyPairHistory(PairHistoryRequestDto pairHistoryRequestDto){
        String baseCurrencyName = pairHistoryRequestDto.getBaseCurrencyName();
        String currencyName = pairHistoryRequestDto.getCurrencyName();

        List<CurrencyPair> retrievedCurrenciesWithoutComputing = checkIfRequireValueComputing(pairHistoryRequestDto);

        if(retrievedCurrenciesWithoutComputing.size() > 0){
            return cutTimeRange(retrievedCurrenciesWithoutComputing, pairHistoryRequestDto);
        } else {
            List<CurrencyPairHistoryPoint> computedCurrency computePairValues
        }


        List<CurrencyPairHistoryPoint> currencyPairHistoryPoints = new ArrayList<>();

        CurrencyPair retrievedCurrency = retrieveCurrency(currencyName);

        List<CurrencyPairHistoryPoint> retrievedCurrencyHistory =
                retrieveRequestedTimeRange(retrievedCurrency, pairHistoryRequestDto);

        String retrievedBaseCurrency = retrievedCurrencyHistory.get(0).getCurrency().getBase();

        if(retrievedBaseCurrency == baseCurrencyName){
            currencyPairHistoryPoints = retrievedCurrencyHistory;
        } else {
            currencyPairHistoryPoints = computeValueBasedOnBaseCurrency(retrievedCurrencyHistory, pairHistoryRequestDto);
        }
        return currencyPairHistoryPoints;
    }

    private List<CurrencyPair> checkIfRequireValueComputing(PairHistoryRequestDto pairHistoryRequestDto){
        String baseCurrencyName = pairHistoryRequestDto.getBaseCurrencyName();
        String currencyName = pairHistoryRequestDto.getCurrencyName();

        List<CurrencyPair> retrievedBaseCurrency = currencyService.retrieveCurrencyByBase(baseCurrencyName);
        return retrievedBaseCurrency.stream().filter(currency -> currency.getBase()==currencyName)
                .collect(Collectors.toList());
    }

    private List<CurrencyPairHistoryPoint> cutTimeRange(List<CurrencyPair> currenciesToCut, PairHistoryRequestDto pairHistoryRequestDto){
        if(pairHistoryRequestDto.isRequestAllHistory()){
            return currenciesToCut;
        } else {
            if(pairHistoryRequestDto.getStart()==null && pairHistoryRequestDto.getStop()==null){
                System.out.println("time range is not set");
            } else {
                currenciesToCut = cropFromStart(pairHistoryRequestDto, currenciesToCut);
                currenciesToCut = cropFromStop(pairHistoryRequestDto, currenciesToCut);
                return currenciesToCut;
            }
        }
    }

    private

    private CurrencyPair retrieveCurrency(String currencyName){
        List<CurrencyPair> currencyList = currencyService.retrieveCurrencyByKey(currencyName);
        CurrencyPair currency = new CurrencyPair();
        if(currencyList.size()==1){
            currency = currencyList.get(0);
        } else {
            System.out.println("Currency not found");
        }
        return currency;
    }

    private List<CurrencyPairHistoryPoint> retrieveRequestedTimeRange(CurrencyPair currency, PairHistoryRequestDto pairHistoryRequestDto){
        if(pairHistoryRequestDto.isRequestAllHistory()){
            return currency.getCurrencyPairHistoryPoints();
        } else {
            LocalDateTime start = pairHistoryRequestDto.getStart();
            LocalDateTime stop = pairHistoryRequestDto.getStop();
            if(stop.isAfter(start)){
                return retrieveRequestedTimeRange(start, stop, currency);
            } else {
                System.out.println("Requested time range is not correct.");
                return new ArrayList<>();
            }
        }
    }

    private List<CurrencyPairHistoryPoint> retrieveRequestedTimeRange(LocalDateTime start, LocalDateTime stop, CurrencyPair currency) {
        return currencyHistoryPointRepository.retrieveByTimeRangeAndCurrencyId(start, stop, currency.getId());
    }

    private List<CurrencyPairHistoryPoint> computeValueBasedOnBaseCurrency
            (List<CurrencyPairHistoryPoint> currencyHistory, PairHistoryRequestDto pairHistoryRequestDto){

        CurrencyPair baseCurrency = currencyHistory.get(0).getCurrency();

        List<CurrencyPairHistoryPoint> baseCurrencyHistory = retrieveRequestedTimeRange(pairHistoryRequestDto.getStart(),
                pairHistoryRequestDto.getStop(), baseCurrency);

        LocalDateTime start = currencyHistory.get(0).getTimeStamp();
        LocalDateTime baseStart =  baseCurrencyHistory.get(0).getTimeStamp();

        List<CurrencyPairHistoryPoint> historyComputed = new ArrayList<>();

        if(currencyHistory.size() == baseCurrencyHistory.size() && start.equals(baseStart)){
            for(int i=0; i<baseCurrencyHistory.size(); i++){
                //double computedValue = 1D/(baseCurrencyHistory.get(i).getValue()/currencyHistory.get(i).getValue());
                //historyComputed.add(new CurrencyPairHistoryPoint(baseCurrencyHistory.get(i).getTimeStamp(), computedValue, new Order()));
            }
        } else {
            //implement histories synchronization
            System.out.println("Currency and base currency are different lenght or not starting in the same time.");
        }
        return historyComputed;
    }
}
