package com.finance.service.tradingdatadownloaderutilities;

import com.finance.clients.WorldTradingClient;
import com.finance.config.WorldTradingDownloaderServiceConfig;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class TradingApiProcessor {

    @Autowired
    private WorldTradingClient worldTradingClient;

    @Autowired
    private WorldTradingDownloaderServiceConfig downloaderConfig;

    public Map<String, String> getCurrenciesMap() throws Exception {
        return processToCurrenciesWithValue(getJsonWithTradingData());
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
}
