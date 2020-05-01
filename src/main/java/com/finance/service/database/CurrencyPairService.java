package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.repository.CurrencyPairRepository;
import com.finance.service.database.communicationObjects.ServiceResponse;
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

    public void deleteById(Long id){
        currencyPairRepository.deleteById(id);
    }

    public ServiceResponse saveCurrencyPair(CurrencyPair currencyPair, boolean overwrite) {
        return checkIfCurrencyPairNotNull(currencyPair, overwrite);
    }

    private ServiceResponse checkIfCurrencyPairNotNull(CurrencyPair currencyPair, boolean overwrite){
        if(currencyPair != null) return checkIfCurrencyPairNameNotNull(currencyPair, overwrite);
        else return new ServiceResponse(null, "CurrencyPair is null.");
    }

    private ServiceResponse checkIfCurrencyPairNameNotNull(CurrencyPair currencyPair, boolean overwrite) {
        if(currencyPair.getCurrencyPairName() != null) return checkIfCurrencyPairExistAlready(currencyPair, overwrite);
        else return new ServiceResponse(null, "CurrencyPair name is null");
    }

    private ServiceResponse checkIfCurrencyPairExistAlready(CurrencyPair currencyPair, boolean overwrite) {
        Optional<CurrencyPair> pair = currencyPairRepository.findByCurrencyName(currencyPair.getCurrencyPairName());
        if(pair.isPresent()) return overwriteIfPossible(currencyPair, pair, overwrite);
        else return addCurrencyPair(currencyPair);
    }

    private ServiceResponse overwriteIfPossible(CurrencyPair newPair, Optional<CurrencyPair> pair, boolean overwrite) {
        if(overwrite == true) return overwriteCurrencyPair(newPair, pair);
        else return new ServiceResponse(null, "CurrencyPair overwriting is not allowed.");
    }

    private ServiceResponse overwriteCurrencyPair(CurrencyPair newPair, Optional<CurrencyPair> pair) {
        currencyPairRepository.deleteById(pair.get().getId());
        return addCurrencyPair(newPair);
    }

    private ServiceResponse addCurrencyPair(CurrencyPair currencyPair){
        currencyPairRepository.save(currencyPair);
        return new ServiceResponse(null, "CurrencyPair saved");
    }
}