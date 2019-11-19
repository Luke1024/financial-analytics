package com.finance.data.service.currency;

import com.finance.data.domain.currency.Currency;
import com.finance.data.domain.currency.CurrencyHistoryPoint;
import com.finance.data.domain.currency.Order;
import com.finance.data.mapper.currency.currency.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    public CurrencyHistoryPoint getLastCurrencyHistoryPoint(String currencyKey){
        List<Currency> currencies = currencyRepository.findByCurrencyName(currencyKey);
        if(currencies.size()==1) {
            return currencies.get(0).getCurrencyHistoryPoints().get(currencies.get(0).getCurrencyHistoryPoints().size()-1);
        } else {
            return new CurrencyHistoryPoint();
        } //currency not found
    }

    public List<Currency> retrieveCurrencyByKey(String value){
        return currencyRepository.findByCurrencyName(value);
    }

    public List<Currency> getCurrencies(){
        return currencyRepository.findAll();
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

    private CurrencyHistoryPoint getCurrencyHistoryPoint(LocalDateTime currentTime, String value, Currency currency){
        return new CurrencyHistoryPoint(currentTime, Double.parseDouble(value), currency, new Order());
    }
}
