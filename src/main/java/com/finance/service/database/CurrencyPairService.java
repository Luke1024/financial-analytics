package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.repository.CurrencyPairRepository;
import com.finance.service.database.communicationObjects.DatabaseEntity;
import com.finance.service.database.communicationObjects.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CurrencyPairService {
    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    public ServiceResponse getCurrencies() {
        List<CurrencyPair> currencyPairs = currencyPairRepository.findAll();
        return new ServiceResponse(
                currencyPairs.stream().map(pair -> (DatabaseEntity) pair)
                        .collect(Collectors.toList()), "", true);
    }

    public ServiceResponse getCurrencyPair(String currencyPairName) {
        return checkIfCurrencyPairNotNull(currencyPairName);
    }

    public ServiceResponse deleteById(Long id){
        return deleteCurrencyPair(id);
    }

    public ServiceResponse saveCurrencyPair(CurrencyPair currencyPair, boolean overwrite) {
        return checkIfCurrencyPairNotNull(currencyPair, overwrite);
    }



    //getCurrencyPair methods
    private ServiceResponse checkIfCurrencyPairNotNull(String currencyPairName){
        if(currencyPairName != null) return findByCurrencyPairName(currencyPairName);
        else return new ServiceResponse(null, "CurrencyPairName is null", false);
    }

    private ServiceResponse findByCurrencyPairName(String currencyPairName){
        Optional<CurrencyPair> currencyPair = currencyPairRepository.findByCurrencyName(currencyPairName);
        if(currencyPair.isPresent()) return new ServiceResponse(Arrays.asList(currencyPair.get()), "CurrencyPair found.", true);
        else return new ServiceResponse(null, "CurrencyPair not found.", false);
    }

    //deleteById methods
    private ServiceResponse deleteCurrencyPair(Long id){
        currencyPairRepository.deleteById(id);
        return checkIfCurrencyStillExists(id);
    }

    private ServiceResponse checkIfCurrencyStillExists(Long id){
        Optional<CurrencyPair> currencyPair = currencyPairRepository.findById(id);
        if(currencyPair.isPresent()) return new ServiceResponse(null, "CurrencyPair id: " + id + " deletion issues.", true);
        else return new ServiceResponse(null, "CurrencyPair id: " + id + " removed.", false);
    }

    //saveCurrencyPair methods
    private ServiceResponse checkIfCurrencyPairNotNull(CurrencyPair currencyPair, boolean overwrite){
        if(currencyPair != null) return checkIfCurrencyPairNameNotNull(currencyPair, overwrite);
        else return new ServiceResponse(null, "CurrencyPair is null.", false);
    }

    private ServiceResponse checkIfCurrencyPairNameNotNull(CurrencyPair currencyPair, boolean overwrite) {
        if(currencyPair.getCurrencyPairName() != null) return checkIfCurrencyPairExistAlready(currencyPair, overwrite);
        else return new ServiceResponse(null, "CurrencyPair name is null", false);
    }

    private ServiceResponse checkIfCurrencyPairExistAlready(CurrencyPair currencyPair, boolean overwrite) {
        Optional<CurrencyPair> pair = currencyPairRepository.findByCurrencyName(currencyPair.getCurrencyPairName());
        if(pair.isPresent()) return overwriteIfPossible(currencyPair, pair, overwrite);
        else return addCurrencyPair(currencyPair);
    }

    private ServiceResponse overwriteIfPossible(CurrencyPair newPair, Optional<CurrencyPair> pair, boolean overwrite) {
        if(overwrite == true) return overwriteCurrencyPair(newPair, pair);
        else return new ServiceResponse(null, "CurrencyPair overwriting is not allowed.", false);
    }

    private ServiceResponse overwriteCurrencyPair(CurrencyPair newPair, Optional<CurrencyPair> pair) {
        currencyPairRepository.deleteById(pair.get().getId());
        return addCurrencyPair(newPair);
    }

    private ServiceResponse addCurrencyPair(CurrencyPair currencyPair){
        currencyPairRepository.save(currencyPair);
        return new ServiceResponse(null, "CurrencyPair saved", true);
    }
}