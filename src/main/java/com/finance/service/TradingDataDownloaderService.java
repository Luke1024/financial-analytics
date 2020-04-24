package com.finance.service;

import com.finance.clients.WorldTradingClient;
import com.finance.config.WorldTradingDownloaderServiceConfig;
import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.service.database.CurrencyPairService;
import com.finance.service.utilities.CurrencyPairAssembler;
import com.finance.service.utilities.PairDto;
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
    private CurrencyPairService currencyPairService;

    @Autowired
    private CurrencyPairAssembler currencyPairAssembler;

    public void downloadCurrentTradingDataForStocks(LocalDateTime currentDateTime) throws Exception {
        Map<String, String> currenciesMap = processToCurrenciesWithValue(getJsonWithTradingData());
        List<PairDto> currencyPairs = assemblyRelevantPairs(currenciesMap);
        addCurrencyPairHistoryPoints(currencyPairs);
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

    private List<PairDto> assemblyRelevantPairs(Map<String, String> currencyMap){
        List<PairDto> currencyPairs = new ArrayList<>();
        for(String pair : downloaderConfig.getCurrencyPairs()){
            currencyPairs.add(assemblyPair(pair, currencyMap));
        }
        return currencyPairs;
    }

    private PairDto assemblyPair(String pair, Map<String, String> currencyMap) {
        return currencyPairAssembler.assembleCurrencyPair(pair, currencyMap);
    }

    private void addCurrencyPairHistoryPoints(List<PairDto> currencies) {
        addHistoryPoints(currencies, currencyPairService.getCurrencyPair(currencies.get(0).getPairName()));
    }

    private void addHistoryPoints(List<PairDto> currencies, Optional<CurrencyPair> currencyPair) {
        if(currencyPair.get() != null) {
            for(PairDto pairDto : currencies) {
                currencyPair.get().getCurrencyPairDataPoints()
                        .add(mapToCurrencyHistoryPoint(pairDto, currencyPair.get()));
            }
        } else {
            addCurrencyPair(currencies);
            addHistoryPoints(currencies, currencyPairService.getCurrencyPair(currencies.get(0).getPairName()));
        }
    }

    private void addCurrencyPair(List<PairDto> currencies){
        CurrencyPair currencyPair = mapToCurrencyPair(currencies.get(0).getPairName());
        currencyPairService.saveCurrencyPair(currencyPair);
    }

    private CurrencyPairDataPoint mapToCurrencyHistoryPoint(PairDto pairDto, CurrencyPair currencyPair){
        return new CurrencyPairDataPoint(LocalDateTime.now(), pairDto.getValue(), currencyPair);
    }

    private CurrencyPair mapToCurrencyPair(String pairName) {
        return new CurrencyPair(pairName);
    }
}