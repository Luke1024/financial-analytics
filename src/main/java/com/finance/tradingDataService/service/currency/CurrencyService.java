package com.finance.tradingDataService.service.currency;

import com.finance.tradingDataService.domain.Currency;
import com.finance.tradingDataService.domain.CurrencyHistoryPoint;
import com.finance.tradingDataService.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> retrieveCurrencyByKey(String value){
        return currencyRepository.findByCurrencyName(value);
    }

    public List<Currency> getCurrencies(){
        return currencyRepository.findAll();
    }

    public void addHistoryPoint(String key, String value){
        List<Currency> currencyList = currencyRepository.findByCurrencyName(key);

        if(currencyList.size()==1){
            Currency retrievedCurency = currencyList.get(0);
            CurrencyHistoryPoint currencyHistoryPoint = getCurrencyHistoryPoint(value, retrievedCurency);
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

    public void addCurrency(String key, String baseCurrency){
        List<CurrencyHistoryPoint> currencyHistoryPoints = new ArrayList<>();
        Currency currency = new Currency(baseCurrency, key, currencyHistoryPoints);
        List<Currency> currencyList = currencyRepository.findByCurrencyName(key);
        if(currencyList.size()==0){
            currencyRepository.save(currency);
        } else {
            System.out.println("Currency already exist.");
        }
    }



    private CurrencyHistoryPoint getCurrencyHistoryPoint(String value, Currency currency){
        return new CurrencyHistoryPoint(LocalDateTime.now(), Double.parseDouble(value), currency);
    }
}
