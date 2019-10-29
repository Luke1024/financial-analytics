package com.finance.tradingDataService.service;

import com.finance.tradingDataService.domain.Currency;
import com.finance.tradingDataService.repository.CurrencyHistoryPointRepository;
import com.finance.tradingDataService.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyHistoryPointRepository currencyHistoryPointRepository;

    public void addHistoryPoint(String value){
        List<Currency> retrievedCurrency = currencyRepository.retrieveByName(value);
        if(retrievedCurrency.size()==1){
            retrievedCurrency.get(0).getCurrencyHistoryPoints().add();
        }
    }
}
