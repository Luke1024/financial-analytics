package com.finance.data.service;

import com.finance.data.service.datadownloader.TradingDataDownloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TradingDataDownloaderServiceTest {
    @Autowired
    private TradingDataDownloaderService downloaderService;

    //@Test
    //public void testDownloading() throws Exception {
        //downloaderService.downloadCurrentTradingDataForStocks();
    //}
}