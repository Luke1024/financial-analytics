package com.finance.data.service.datadownloader;

import com.finance.data.clients.WorldTradingClient;
import com.finance.data.config.WorldTradingDownloaderServiceConfig;
import com.finance.data.domain.currency.CurrencyPair;
import com.finance.data.service.currency.CurrencyService;
import com.finance.data.service.currency.utilities.CurrencyPairAssembler;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class TradingDataDownloaderService {

    @Autowired
    private WorldTradingClient worldTradingClient;

    @Autowired
    private WorldTradingDownloaderServiceConfig downloaderConfig;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyPairAssembler currencyPairAssembler;

    private final String baseCurrency = "USD";

    public void downloadCurrentTradingDataForStocks(LocalDateTime currentDateTime) throws Exception {
         Map<String, String> currenciesMap = processToCurrenciesWithValue(getJsonWithTradingData());
         List<CurrencyPair> currencyPairs = assemblyRelevantPairs(currenciesMap);
    }

    private JSONObject getJsonWithTradingData() throws Exception {
        String result = worldTradingClient.getCurrentUsdBasedCurrencyValues();
        JSONObject jObj = new JSONObject(result);
        return jObj.getJSONObject("data");
    }

    private HashMap<String, String> processToCurrenciesWithValue(JSONObject currencies){
        Set<String> currencyKeys = currencies.keySet();
        HashMap<String, String> currenciesProcessed = new HashMap<>();
        for(String key: currencyKeys){
            currenciesProcessed.put(key, currencies.getString(key));
        }
        return currenciesProcessed;
    }

    private List<CurrencyPair> assemblyRelevantPairs(Map<String, String> currencyMap){
        List<CurrencyPair> currencyPairs = new ArrayList<>();
        for(String pair : downloaderConfig.getCurrencyPairs()){
            currencyPairs.add(assemblyPair(pair, currencyMap));
        }
        return currencyPairs;
    }

    private CurrencyPair assemblyPair(String pair, Map<String, String> currencyMap) {

    }
}
    /*
        Map<String, String> currenciesMap = processToCurrencies(currencies);

        for(Map.Entry<String, String> entry : currenciesMap.entrySet()){
            if(checkIfCurrencyExist(entry.getKey())){
                addHistoryPoint(entry, currentDateTime);
            } else {
                addCurrency(entry.getKey());
                addHistoryPoint(entry, currentDateTime);
            }
        }
    }

    private HashMap<String, String> processToCurrencies(JSONObject currencies){
        Set<String> currencyKeys = currencies.keySet();
        HashMap<String, String> currenciesProcessed = new HashMap<>();
        for(String key: currencyKeys){
            currenciesProcessed.put(key, currencies.getString(key));
        }
        return currenciesProcessed;
    }

    private boolean checkIfCurrencyExist(String value){
        return currencyService.retrieveCurrencyByKey(value).size()>0;
    }

    private void addHistoryPoint(Map.Entry<String, String> entry, LocalDateTime currentDateTime){
        currencyService.addHistoryPoint(entry.getKey(), entry.getValue(), currentDateTime);
    }

    private void addCurrency(String key){
        currencyService.addCurrency(key, baseCurrency);
    }
}
*/
