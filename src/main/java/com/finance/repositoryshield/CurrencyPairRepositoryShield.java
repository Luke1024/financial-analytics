package com.finance.repositoryshield;

import com.finance.domain.CurrencyPair;
import com.finance.repository.CurrencyPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyPairRepositoryShield {

    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    public Optional<CurrencyPair> findByCurrencyName(String currencyName){
        return currencyPairRepository.findByCurrencyName(currencyName);
    }

    public void deleteById(Long id){
        currencyPairRepository.deleteById(id);
    }

    public List<CurrencyPair> findAll(){
        return currencyPairRepository.findAll();
    }

    public boolean save(CurrencyPair currencyPair, boolean overwrite){
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
}
