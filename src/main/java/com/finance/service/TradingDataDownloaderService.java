package com.finance.service;

import com.finance.clients.WorldTradingClient;
import com.finance.config.WorldTradingDownloaderServiceConfig;
import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.service.database.CurrencyPairDataPointService;
import com.finance.service.database.CurrencyPairService;
import com.finance.service.database.communicationObjects.DatabaseEntity;
import com.finance.service.database.communicationObjects.DatabaseResponse;
import com.finance.service.tradingDataDownloaderUtilities.CurrencyPairAssembler;
import com.finance.service.tradingDataDownloaderUtilities.PairDto;
import com.finance.service.tradingDataDownloaderUtilities.TradingApiProcessor;
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
    private CurrencyPairDataPointService currencyPairDataPointService;

    @Autowired
    private CurrencyPairAssembler currencyPairAssembler;

    private TradingApiProcessor tradingApiProcessor;

    @Autowired
    private CurrencyPairService currencyPairService;


    public void downloadCurrentTradingDataForStocks(LocalDateTime currentDateTime) throws Exception {
        Map<String, String> currenciesMap = tradingApiProcessor.getCurrenciesMap();
        List<PairDto> pairsDto = currencyPairAssembler.assembleCurrencyPair(currenciesMap);
        addCurrencyPairHistoryPoints(pairsDto);
    }

    private void addCurrencyPairHistoryPoints(List<PairDto> currencies) {
        String pairName = currencies.get(0).getPairName();

        DatabaseResponse databaseResponse = currencyPairService.getCurrencyPair(currencies.get(0).getPairName());

        CurrencyPair currencyPair = null;
        if(databaseResponse.isOK()){
            if(databaseResponse.getRequestedObjects().size()==1){
                DatabaseEntity databaseEntity = databaseResponse.getRequestedObjects().get(0);
                if(databaseEntity instanceof CurrencyPair){
                    currencyPair = (CurrencyPair) databaseEntity;
                }
            }
        }

        addHistoryPoints(currencies, currencyPair);
    }

    private void addHistoryPoints(List<PairDto> currencies, CurrencyPair currencyPair) {
        List<CurrencyPairDataPoint> currencyPairDataPoints = new ArrayList<>();
        for(PairDto pairDto : currencies) {
            currencyPairDataPoints.add(mapToCurrencyHistoryPoint(pairDto, currencyPair));
        }
        currencyPairDataPointService.addDataPoints(currencyPairDataPoints, currencyPair.getCurrencyPairName());
    }

    private CurrencyPairDataPoint mapToCurrencyHistoryPoint(PairDto pairDto, CurrencyPair currencyPair){
        return new CurrencyPairDataPoint(LocalDateTime.now(), pairDto.getValue(), currencyPair);
    }
}