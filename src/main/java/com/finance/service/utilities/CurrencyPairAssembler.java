package com.finance.service.utilities;

import com.finance.config.WorldTradingDownloaderServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CurrencyPairAssembler {
    @Autowired
    private WorldTradingDownloaderServiceConfig config;

    public PairDto assembleCurrencyPair(String pair, Map<String, String> currencyMap){
        String baseCurrency;
        String currency;
        try {
            baseCurrency = pair.substring(0,3);
            currency = pair.substring(3,5);
        } catch (Exception e){
            return null;
        }
        if(baseCurrency.equals(config.getApiSetBaseCurrency())){
            return assemblePairWithBaseCurrencyFirst(currency, currencyMap);
        }
        if(currency.equals(config.getApiSetBaseCurrency())){
            return assemblePairWithBaseCurrencyLast(baseCurrency, currencyMap);
        }
        return assemblePairExcludingBaseCurrency(pair, currencyMap);
    }

    private PairDto assemblePairWithBaseCurrencyFirst(String currency, Map<String, String> currencyMap) {
        try {
            double value = Double.parseDouble(currencyMap.get(currency));
            return new PairDto(config.getApiSetBaseCurrency() + "/"+ currency, value);
        } catch (Exception e){
            return null;
        }
    }

    private PairDto assemblePairWithBaseCurrencyLast(String baseCurrency, Map<String, String> currencyMap) {
        try {
            double value = 1/Double.parseDouble(currencyMap.get(baseCurrency));
            return new PairDto(baseCurrency + "/" + config.getApiSetBaseCurrency(), value);
        } catch (Exception e){
            return null;
        }
    }

    private PairDto assemblePairExcludingBaseCurrency(String pair, Map<String, String> currencyMap) {
        String baseCurrency = pair.substring(0,2);
        String currency = pair.substring(3,5);
        try {
            double baseValue = Double.parseDouble(currencyMap.get(baseCurrency));
            double currencyValue = Double.parseDouble(currencyMap.get(currency));
            return new PairDto(pair, baseValue/currencyValue);
        } catch (Exception e) {
            return null;
        }
    }
}
