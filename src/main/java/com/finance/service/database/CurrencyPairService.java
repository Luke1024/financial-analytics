package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.repository.CurrencyPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class CurrencyPairService {
    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    private Logger logger = Logger.getLogger(CurrencyPairService.class.getName());

    public List<CurrencyPair> getCurrencies() {
        return currencyPairRepository.findAll();
    }

    public CurrencyPair getCurrencyPair(String currencyPairName) {
        Optional<CurrencyPair> currencyPair = currencyPairRepository.findByCurrencyName(currencyPairName);
        if(currencyPair.isPresent()){
            return currencyPair.get();
        } else {
            logger.log(Level.INFO, "CurrencyPair " + currencyPairName + " not found.");
            return null;
        }
    }

    public void deleteById(Long id){
        currencyPairRepository.deleteById(id);
    }

    public void saveCurrencyPair(CurrencyPair currencyPair, boolean overwrite) {
        if(overwrite){
            currencyPairRepository.save(currencyPair);
        } else {
            String name = currencyPair.getCurrencyPairName();
            if(name == null) return;
            Optional<CurrencyPair> pair = currencyPairRepository.findByCurrencyName(name);
            if( ! pair.isPresent()) {
                currencyPairRepository.save(currencyPair);
            } else {
                logger.log(Level.INFO, "CurrencyPair " + name + " already exist and overwriting not allowed.");
            }
        }
    }
}