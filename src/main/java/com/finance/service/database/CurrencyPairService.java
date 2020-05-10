package com.finance.service.database;

import com.finance.domain.CurrencyPair;
import com.finance.repository.CurrencyPairRepository;
import com.finance.service.database.communicationObjects.DatabaseEntity;
import com.finance.service.database.communicationObjects.DatabaseResponse;
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

    public DatabaseResponse getCurrencies() {
        List<CurrencyPair> currencyPairs = currencyPairRepository.findAll();
        return new DatabaseResponse(
                currencyPairs.stream().map(pair -> (DatabaseEntity) pair)
                        .collect(Collectors.toList()), "", true);
    }

    public DatabaseResponse getCurrencyPair(String currencyPairName) {
        return checkIfCurrencyPairNotNull(currencyPairName);
    }

    public DatabaseResponse deleteById(Long id){
        return deleteCurrencyPair(id);
    }

    public DatabaseResponse saveCurrencyPair(CurrencyPair currencyPair, boolean overwrite) {
        return checkIfCurrencyPairNotNull(currencyPair, overwrite);
    }



    //getCurrencyPair methods
    private DatabaseResponse checkIfCurrencyPairNotNull(String currencyPairName){
        if(currencyPairName != null) return findByCurrencyPairName(currencyPairName);
        else return new DatabaseResponse(null, "CurrencyPairName is null.", false);
    }

    private DatabaseResponse findByCurrencyPairName(String currencyPairName){
        Optional<CurrencyPair> currencyPair = currencyPairRepository.findByCurrencyName(currencyPairName);
        if(currencyPair.isPresent()) return new DatabaseResponse(Arrays.asList(currencyPair.get()), "CurrencyPair found.", true);
        else return new DatabaseResponse(null, "CurrencyPair not found.", false);
    }

    //deleteById methods
    private DatabaseResponse deleteCurrencyPair(Long id){
        return checkIfCurrencyExists(id);
    }

    private DatabaseResponse checkIfCurrencyExists(long id){
        Optional<CurrencyPair> currencyPair = currencyPairRepository.findById(id);
        if( ! currencyPair.isPresent()){
            return new DatabaseResponse(null,"CurrencyPair not found.",false);
        } else {
            currencyPairRepository.deleteById(id);
            return checkIfCurrencyStillExists(id);
        }
    }

    private DatabaseResponse checkIfCurrencyStillExists(Long id){
        Optional<CurrencyPair> currencyPair = currencyPairRepository.findById(id);
        if(currencyPair.isPresent()) return new DatabaseResponse(null, "CurrencyPair id: " + id + " deletion issues.", false);
        else return new DatabaseResponse(null, "CurrencyPair id: " + id + " removed.", true);
    }

    //saveCurrencyPair methods
    private DatabaseResponse checkIfCurrencyPairNotNull(CurrencyPair currencyPair, boolean overwrite){
        if(currencyPair != null) return checkIfCurrencyPairNameNotNull(currencyPair, overwrite);
        else return new DatabaseResponse(null, "CurrencyPair is null.", false);
    }

    private DatabaseResponse checkIfCurrencyPairNameNotNull(CurrencyPair currencyPair, boolean overwrite) {
        if(currencyPair.getCurrencyPairName() != null) return checkIfCurrencyPairExistAlready(currencyPair, overwrite);
        else return new DatabaseResponse(null, "CurrencyPair name is null.", false);
    }

    private DatabaseResponse checkIfCurrencyPairExistAlready(CurrencyPair currencyPair, boolean overwrite) {
        Optional<CurrencyPair> pair = currencyPairRepository.findByCurrencyName(currencyPair.getCurrencyPairName());
        if(pair.isPresent()) return overwriteIfPossible(currencyPair, pair, overwrite);
        else return addCurrencyPair(currencyPair, null);
    }

    private DatabaseResponse overwriteIfPossible(CurrencyPair newPair, Optional<CurrencyPair> pair, boolean overwrite) {
        if(overwrite == true) return overwriteCurrencyPair(newPair, pair);
        else return new DatabaseResponse(null, "CurrencyPair exists, overwriting is not allowed.", false);
    }

    private DatabaseResponse overwriteCurrencyPair(CurrencyPair newPair, Optional<CurrencyPair> pair) {
        currencyPairRepository.deleteById(pair.get().getId());
        return addCurrencyPair(newPair, "CurrencyPair overwritten. ");
    }

    private DatabaseResponse addCurrencyPair(CurrencyPair currencyPair, String message){
        currencyPairRepository.save(currencyPair);
        return new DatabaseResponse(null, message + "CurrencyPair saved.", true);
    }
}