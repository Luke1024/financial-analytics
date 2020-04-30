package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.repository.CurrencyPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CurrencyPairService {
    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    public List<CurrencyPair> getCurrencies() {
        return currencyPairRepository.findAll();
    }

    public Optional<CurrencyPair> getCurrencyPair(String currencyPairName) {
        return currencyPairRepository.findByCurrencyName(currencyPairName);
    }

    public boolean saveCurrencyPair(CurrencyPair currencyPair, boolean overwrite){
        Optional<CurrencyPair> pair = currencyPairRepository.findByCurrencyName(currencyPair.getCurrencyPairName());
        if(pair.isPresent()) {
            if(overwrite == true) {
                currencyPairRepository.deleteById(pair.get().getId());
                currencyPairRepository.save(currencyPair);
            } else return false;
        }
        currencyPairRepository.save(currencyPair);
        return true;
    }

    public void deleteById(Long id){
        currencyPairRepository.deleteById(id);
    }
}