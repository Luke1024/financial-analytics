package com.finance.data.service.currency;

import com.finance.data.domain.currency.CurrencyPair;
import com.finance.data.domain.currency.CurrencyPairHistoryPoint;
import com.finance.data.repository.currency.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    public CurrencyPairHistoryPoint getLastCurrencyHistoryPoint(String currencyKey){
        List<CurrencyPair> currencies = currencyRepository.findByCurrencyName(currencyKey);
        if(currencies.size()==1) {
            int size = currencies.get(0).getCurrencyPairHistoryPoints().size();
            System.out.println(size);
            return currencies.get(0).getCurrencyPairHistoryPoints().get(size-1);
        } else {
            if(currencies.size()==0){
                System.out.println("Currency not found");
            } else {
                System.out.println("Multiple currencies found");
            }

            return new CurrencyPairHistoryPoint();
        } //currency not found
    }

    public List<CurrencyPair> retrieveCurrencyByKey(String value){
        return currencyRepository.findByCurrencyName(value);
    }

    public List<CurrencyPair> retrieveCurrencyByBase(String value){
        return currencyRepository.findByBase(value);
    }

    public List<String> getCurrencies(){
        List<CurrencyPair> currencies = currencyRepository.findAll();

        List<String> currenciesNames = currencies.stream()
                .map(currency -> currency.getCurrencyPairName())
                .collect(Collectors.toList());

        List<String> baseCurrencies = currencies.stream()
                .map(currency -> currency.getBase())
                .collect(Collectors.toList());

        currenciesNames.addAll(baseCurrencies);

        HashSet<String> currenciesAsSet = new HashSet<String>(currenciesNames);

        return new ArrayList<String>(currenciesAsSet);
    }

    public void addHistoryPoint(String key, String value, LocalDateTime currentDateTime){
        List<CurrencyPair> currencyList = currencyRepository.findByCurrencyName(key);

        if(currencyList.size()==1){
            CurrencyPair retrievedCurency = currencyList.get(0);
            CurrencyPairHistoryPoint currencyPairHistoryPoint = getCurrencyHistoryPoint(currentDateTime, value, retrievedCurency);
            retrievedCurency.getCurrencyPairHistoryPoints().add(currencyPairHistoryPoint);
            currencyRepository.save(retrievedCurency);
        } else {
            if(currencyList.size()==0){
                System.out.println("No currency found");
            }
            if(currencyList.size()>1){
                System.out.println("Detected currency duplicates");
            }
        }
    }

    public void addCurrency(String baseCurrency, String key){
        List<CurrencyPairHistoryPoint> currencyPairHistoryPoints = new ArrayList<>();
        CurrencyPair currency = new CurrencyPair(baseCurrency, key);
        List<CurrencyPair> currencyList = currencyRepository.findByCurrencyName(key);
        if(currencyList.size()==0){
            currencyRepository.save(currency);
        } else {
            System.out.println("Currency already exist.");
        }
    }

    private CurrencyPairHistoryPoint getCurrencyHistoryPoint(LocalDateTime currentTime, String value, CurrencyPair currency){
        return new CurrencyPairHistoryPoint(currentTime, Double.parseDouble(value), currency);
    }
}
