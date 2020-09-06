package com.finance.service.database.pairdatapointserviceutilities;

import com.finance.domain.CurrencyPair;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CurrencyPairDataPointCache {

    @Autowired
    private CurrencyPairRepository pairRepository;

    @Autowired
    private CurrencyPairHistoryPointRepository pointRepository;

    private Logger logger = Logger.getLogger(CurrencyPairDataPointCache.class.getName());

    private List<CurrencyPair> currencyPairs = new ArrayList<>();

    private boolean loaded = false;


    @PostConstruct
    public void init() {
        logger.log(Level.INFO, "Started loading currency data to cache.");
        try {
            currencyPairs = pairRepository.findAll();
            System.out.println(currencyPairs.size());
            logger.log(Level.INFO, "Loaded " + currencyPairs.size() + " pairs.");
            for (CurrencyPair currencyPair : currencyPairs) {
                logger.log(Level.INFO, currencyPair.getCurrencyPairName() + " with: "
                        + currencyPair.getCurrencyPairDataPoints().size() + " points.");
            }
            loaded = true;
        } catch (Exception e){
            logger.log(Level.SEVERE, e.getCause() + " Problem with currency data caching. Shutting down server.");
            System.exit(0);
        }
    }

    public void saveCurrencyPair(CurrencyPair currencyPair) {
        if(currencyPair != null){
            if(currencyPair.getCurrencyPairName() != null){
                 if(currencyPairs != null){
                     deleteCurrencyPair(currencyPair);
                     saveUpdatedPair(currencyPair);
                 }
            }
        }
    }

    private void deleteCurrencyPair(CurrencyPair pair){
        List<CurrencyPair> pairsToDelete = currencyPairs.stream()
                .filter(currencyPair -> currencyPair.getCurrencyPairName().equals(pair.getCurrencyPairName()))
                .collect(Collectors.toList());
        for(CurrencyPair currencyPair : pairsToDelete){
            currencyPairs.remove(currencyPair);
        }
    }

    private void saveUpdatedPair(CurrencyPair currencyPair){
        currencyPairs.add(currencyPair);
    }

    public Optional<CurrencyPair> findByCurrencyName(String name) {
        for (CurrencyPair pair : currencyPairs) {
            if (pair.getCurrencyPairName().equals(name)) {
                return Optional.of(pair);
            }
        }
        return Optional.empty();
    }
}
