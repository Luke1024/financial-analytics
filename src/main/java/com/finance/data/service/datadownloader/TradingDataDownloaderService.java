package com.finance.data.service.datadownloader;

import com.finance.data.clients.WorldTradingClient;
import com.finance.data.service.currency.CurrencyService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class TradingDataDownloaderService {

    @Autowired
    private WorldTradingClient worldTradingClient;

    @Autowired
    private CurrencyService currencyService;

    private final String baseCurrency = "USD";

    public void downloadCurrentTradingDataForStocks(LocalDateTime currentDateTime) throws Exception {
        String result = worldTradingClient.getCurrentUsdBasedCurrencyValues();
        JSONObject jObj = new JSONObject(result);
        JSONObject currencies = jObj.getJSONObject("data");
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