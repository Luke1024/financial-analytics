package com.finance.service.database.pairdatapointserviceutilities;

import com.finance.domain.CurrencyPair;
import com.finance.repository.CurrencyPairHistoryPointRepository;
import com.finance.repository.CurrencyPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CurrencyPairDataPointCache {

    @Autowired
    private CurrencyPairRepository pairRepository;

    @Autowired
    private CurrencyPairHistoryPointRepository pointRepository;

    private Logger logger = Logger.getLogger(CurrencyPairDataPointCache.class.getName());

    private List<CurrencyPair> currencyPairs;

    private boolean loaded = false;

    public boolean isLoaded() {
        return loaded;
    }

    public void load() {
        logger.log(Level.INFO, "Started loading currency data to cache.");
        try {
            currencyPairs = pairRepository.findAll();
            logger.log(Level.INFO, "Loaded " + currencyPairs.size() + " pairs.");
            for (CurrencyPair currencyPair : currencyPairs) {
                logger.log(Level.INFO, currencyPair.getCurrencyPairName() + " with: "
                        + currencyPair.getCurrencyPairDataPoints().size() + " points.");
            }
            loaded = true;
        } catch (Exception e){
            logger.log(Level.SEVERE, e.toString() + " Problem with currency data caching. Shutting down server.");
            System.exit(0);
        }
    }

    public Optional<CurrencyPair> findByCurrencyName(String name){
        for(CurrencyPair pair : currencyPairs){
            if(pair.getCurrencyPairName() == name) return Optional.of(pair);
        }
        return Optional.empty();
    }
}
