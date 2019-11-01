package com.finance.tradingDataService.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class TradingDataDownloaderServiceTest {
    @Autowired
    private TradingDataDownloaderService downloaderService;

    @Test
    public void testDownloading() throws Exception {
        downloaderService.downloadCurrentTradingDataForStocks();
    }
}