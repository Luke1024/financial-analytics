package com.finance.tradingDataService.service.currency;

import com.finance.tradingDataService.domain.Currency;
import com.finance.tradingDataService.domain.CurrencyHistoryPoint;
import com.finance.tradingDataService.domain.dto.PairHistoryRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyPairService {
    @Autowired
    private CurrencyService currencyService;

    public List<CurrencyHistoryPoint> getCurrencyPairHistory(PairHistoryRequestDto pairHistoryRequestDto){
        String baseCurrencyName = pairHistoryRequestDto.getBaseCurrencyName();
        String currencyName = pairHistoryRequestDto.getCurrencyName();
        List<CurrencyHistoryPoint> currencyHistoryPoints = new ArrayList<>();

        Currency retrievedCurrency = retrieveCurrency(currencyName);

        List<CurrencyHistoryPoint> retrievedHistory = retrieveRequestedTimeRange(retrievedCurrency, pairHistoryRequestDto);

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
                //return retrieveRequestedTimeRange(start, stop, currency);
            } else {
                System.out.println("Requested time range is not correct.");
                return new ArrayList<>();
            }
        }
    }
/*
    private List<CurrencyHistoryPoint> retrieveRequestedTimeRange(LocalDateTime start, LocalDateTime stop, Currency currency) {
        List<CurrencyHistoryPoint> historyPoints = currency.getCurrencyHistoryPoints();
        Long startIndex;
        Long stopIndex;

        for(CurrencyHistoryPoint point : currency.getCurrencyHistoryPoints()) {
            point.getTimeStamp().equals(start)
        }

    }
*/















    private List<CurrencyHistoryPoint> analyzeRequiredHistory(Currency currency, PairHistoryRequestDto requestDto){

        List<CurrencyHistoryPoint> currencyHistoryPoints = new ArrayList<>();

        if(currency.getBase() == requestDto.getBaseCurrencyName()) {
            currencyHistoryPoints = currency.getCurrencyHistoryPoints();
        } else {
            List<Currency> baseCurrencyList = currencyService.retrieveCurrencyByKey(requestDto.getBaseCurrencyName());
            if(baseCurrencyList.size()==1){
                currencyHistoryPoints = computeValueBasedOnBaseCurrency(baseCurrencyList.get(0), currency);
            } else {
                System.out.println("Currency not found");
            }
        }
        return currencyHistoryPoints;
    }

    private List<CurrencyHistoryPoint> computeValueBasedOnBaseCurrency(Currency baseCurrency, Currency currency) {
        List<CurrencyHistoryPoint> currencyHistoryPoints = new ArrayList<>();

    }
}
