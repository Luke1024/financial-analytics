package com.finance.data.service.currency;

import com.finance.data.domain.currency.Currency;
import com.finance.data.domain.currency.CurrencyHistoryPoint;
import com.finance.data.domain.currency.dto.PairHistoryRequestDto;
import com.finance.data.repository.currency.CurrencyHistoryPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyPairService {
    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyHistoryPointRepository currencyHistoryPointRepository;

    public List<CurrencyHistoryPoint> getCurrencyPairHistory(PairHistoryRequestDto pairHistoryRequestDto){
        String baseCurrencyName = pairHistoryRequestDto.getBaseCurrencyName();
        String currencyName = pairHistoryRequestDto.getCurrencyName();
        List<CurrencyHistoryPoint> currencyHistoryPoints = new ArrayList<>();

        Currency retrievedCurrency = retrieveCurrency(currencyName);

        List<CurrencyHistoryPoint> retrievedCurrencyHistory =
                retrieveRequestedTimeRange(retrievedCurrency, pairHistoryRequestDto);

        String retrievedBaseCurrency = retrievedCurrencyHistory.get(0).getCurrency().getBase();

        if(retrievedBaseCurrency == baseCurrencyName){
            currencyHistoryPoints = retrievedCurrencyHistory;
        } else {
            currencyHistoryPoints = computeValueBasedOnBaseCurrency(retrievedCurrencyHistory, pairHistoryRequestDto);
        }


        return currencyHistoryPoints;
    }

    private Currency retrieveCurrency(String currencyName){
        List<Currency> currencyList = currencyService.retrieveCurrencyByKey(currencyName);
        Currency currency = new Currency();
        if(currencyList.size()==1){
            currency = currencyList.get(0);
        } else {
            System.out.println("Currency not found");
        }
        return currency;
    }

    private List<CurrencyHistoryPoint> retrieveRequestedTimeRange(Currency currency, PairHistoryRequestDto pairHistoryRequestDto){
        if(pairHistoryRequestDto.isRequestAllHistory()){
            return currency.getCurrencyHistoryPoints();
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

    private List<CurrencyHistoryPoint> retrieveRequestedTimeRange(LocalDateTime start, LocalDateTime stop, Currency currency) {
        return currencyHistoryPointRepository.retrieveByTimeRangeAndCurrencyId(start, stop, currency.getId());
    }

    private List<CurrencyHistoryPoint> computeValueBasedOnBaseCurrency
            (List<CurrencyHistoryPoint> currencyHistory, PairHistoryRequestDto pairHistoryRequestDto){

        Currency baseCurrency = currencyHistory.get(0).getCurrency();

        List<CurrencyHistoryPoint> baseCurrencyHistory = retrieveRequestedTimeRange(pairHistoryRequestDto.getStart(),
                pairHistoryRequestDto.getStop(), baseCurrency);

        LocalDateTime start = currencyHistory.get(0).getTimeStamp();
        LocalDateTime baseStart =  baseCurrencyHistory.get(0).getTimeStamp();

        List<CurrencyHistoryPoint> historyComputed = new ArrayList<>();

        if(currencyHistory.size() == baseCurrencyHistory.size() && start.equals(baseStart)){
            for(int i=0; i<baseCurrencyHistory.size(); i++){
                double computedValue = 1D/(baseCurrencyHistory.get(i).getValue()/currencyHistory.get(i).getValue());
                historyComputed.add(new CurrencyHistoryPoint(baseCurrencyHistory.get(i).getTimeStamp(), computedValue));
            }
        } else {
            //implement histories synchronization
            System.out.println("Currency and base currency are different lenght or not starting in the same time.");
        }
        return historyComputed;
    }
}
