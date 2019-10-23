package com.finance.tradingDataService.service;

import com.finance.tradingDataService.clients.WorldTradingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TradingDataDownloader {
    @Autowired
    private CSVReader csvReader;

    @Autowired
    private WorldTradingClient worldTradingClient;

    private void downloadCurrentTradingDataForStocks() throws Exception {
        List<String> symbols = csvReader.readCsv("/home/luke/financialAnalytics/src/main/resources/symbols/stocklist.csv");

    }
}
