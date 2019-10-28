package com.finance.tradingDataService.service;

import com.finance.tradingDataService.clients.WorldTradingClient;
import com.finance.tradingDataService.domain.Currency;
import com.finance.tradingDataService.repository.CurrencyHistoryPointRepository;
import com.finance.tradingDataService.repository.CurrencyRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TradingDataDownloaderService {

    @Autowired
    private WorldTradingClient worldTradingClient;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyHistoryPointRepository currencyHistoryPointRepository;

    public void downloadCurrentTradingDataForStocks() throws Exception {
        String result = worldTradingClient.getCurrentUsdBasedCurrencyValues();
        JSONObject jObj = new JSONObject(result);
        JSONObject currencyValues = jObj.getJSONObject("data");

        checkCurrencyEntries(currencyValues.keySet());
    }

    private void checkCurrencyEntries(Set<String> values){
        for(String value : values){
            currencyRepository.findAll().
        }
    }
}
