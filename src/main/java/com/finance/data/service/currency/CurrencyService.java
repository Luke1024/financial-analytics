package com.finance.data.service.currency;

import com.finance.data.domain.currency.Currency;
import com.finance.data.domain.currency.CurrencyHistoryPoint;
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

    public CurrencyHistoryPoint getLastCurrencyHistoryPoint(String currencyKey){
        List<Currency> currencies = currencyRepository.findByCurrencyName(currencyKey);
        if(currencies.size()==1) {
            int size = currencies.get(0).getCurrencyHistoryPoints().size();
            System.out.println(size);
            return currencies.get(0).getCurrencyHistoryPoints().get(size-1);
        } else {
            if(currencies.size()==0){
                System.out.println("Currency not found");
            } else {
                System.out.println("Multiple currencies found");
            }

            return new CurrencyHistoryPoint();
        } //currency not found
    }

    public List<Currency> retrieveCurrencyByKey(String value){
        return currencyRepository.findByCurrencyName(value);
    }

    public List<Currency> retrieveCurrencyByBase(String value){
        return currencyRepository.findByBase(value);
    }

    public List<String> getCurrencies(){
        List<Currency> currencies = currencyRepository.findAll();

        List<String> currenciesNames = currencies.stream()
                .map(currency -> currency.getCurrencyName())
                .collect(Collectors.toList());

        List<String> baseCurrencies = currencies.stream()
                .map(currency -> currency.getBase())
                .collect(Collectors.toList());

        currenciesNames.addAll(baseCurrencies);

        HashSet<String> currenciesAsSet = new HashSet<String>(currenciesNames);

        return new ArrayList<String>(currenciesAsSet);
    }

    public void addHistoryPoint(String key, String value, LocalDateTime currentDateTime){
        List<Currency> currencyList = currencyRepository.findByCurrencyName(key);

        if(currencyList.size()==1){
            Currency retrievedCurency = currencyList.get(0);
            CurrencyHistoryPoint currencyHistoryPoint = getCurrencyHistoryPoint(currentDateTime, value, retrievedCurency);
            retrievedCurency.getCurrencyHistoryPoints().add(currencyHistoryPoint);
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
        List<CurrencyHistoryPoint> currencyHistoryPoints = new ArrayList<>();
        Currency currency = new Currency(baseCurrency, key);
        List<Currency> currencyList = currencyRepository.findByCurrencyName(key);
        if(currencyList.size()==0){
            currencyRepository.save(currency);
        } else {
            System.out.println("Currency already exist.");
        }
    }

    private CurrencyHistoryPoint getCurrencyHistoryPoint(LocalDateTime currentTime, String value, Currency currency){
        return new CurrencyHistoryPoint(currentTime, Double.parseDouble(value), currency);
    }
}
