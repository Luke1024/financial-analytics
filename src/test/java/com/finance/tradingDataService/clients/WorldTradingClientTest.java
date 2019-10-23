package com.finance.tradingDataService.clients;

import com.finance.tradingDataService.service.CSVReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class WorldTradingClientTest {
    @Autowired
    private WorldTradingClient worldTradingClient;

    @Autowired
    private CSVReader csvReader;

    @Test
    void getCurrentTradingDataForMultipleStocks() throws Exception {
        List<String> stocks = csvReader.readCsv("/home/luke/financialAnalytics/src/main/resources/symbols/stocklist.csv");
        List<String> stocks500 = stocks.subList(1,500); //start from 1 because 0 is "symbol"

        String stocks500InString = stocks500.stream().collect(Collectors.joining(","));
        String result = worldTradingClient.getCurrentTradingDataForMultipleStocks(stocks500InString);

        System.out.println(result);
    }
}