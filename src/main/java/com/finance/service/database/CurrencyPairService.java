package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CurrencyPairService {
    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    @Autowired
    private CurrencyPairHistoryPointRepository currencyPairHistoryPointRepository;

    public List<CurrencyPair> getCurrencies() {
        return currencyPairRepository.findAll();
    }

    public Optional<CurrencyPair> getCurrencyPair(String currencyPair) {
        return currencyPairRepository.findByCurrencyName(currencyPair);
    }

    public void saveCurrencyPair(CurrencyPair currencyPair){
        Optional<CurrencyPair> pair = currencyPairRepository.findByCurrencyName(currencyPair.getCurrencyPairName());
        if( ! pair.isPresent()){
            currencyPairRepository.save(currencyPair);
        }
    }
}