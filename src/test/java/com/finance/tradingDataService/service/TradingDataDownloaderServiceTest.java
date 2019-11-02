package com.finance.tradingDataService.service;

import com.finance.tradingDataService.service.datadownloader.TradingDataDownloaderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TradingDataDownloaderServiceTest {
    @Autowired
    private TradingDataDownloaderService downloaderService;

    @Test
    public void testDownloading() throws Exception {
        downloaderService.downloadCurrentTradingDataForStocks();
    }
}