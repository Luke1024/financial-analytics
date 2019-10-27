package com.finance.tradingDataService.service;

import com.finance.tradingDataService.clients.WorldTradingClient;
import com.finance.tradingDataService.domain.Currency;
import com.finance.tradingDataService.repository.CurrencyRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TradingDataDownloaderService {

    @Autowired
    private WorldTradingClient worldTradingClient;


    private void downloadCurrentTradingDataForStocks() throws Exception {
        String result = worldTradingClient.getCurrentUsdBasedCurrencyValues();
        JSONObject jObj = new JSONObject(result);
        JSONObject currencyValues = jObj.getJSONObject("data");
    }
}
