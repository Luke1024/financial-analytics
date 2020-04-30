package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.repositoryshield.CurrencyPairRepositoryShield;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CurrencyPairService {
    @Autowired
    private CurrencyPairRepositoryShield currencyPairRepositoryShield;

    public List<CurrencyPair> getCurrencies() {
        return currencyPairRepositoryShield.findAll();
    }

    public Optional<CurrencyPair> getCurrencyPair(String currencyPair) {
        return currencyPairRepositoryShield.findByCurrencyName(currencyPair);
    }

    public boolean saveCurrencyPair(CurrencyPair currencyPair){
        currencyPairRepositoryShield.save(currencyPair, false);
    }

    public boolean saveCurrencyPairWithOvewrite(CurrencyPair currencyPair){
        currencyPairRepositoryShield.save(currencyPair, true);
    }
}