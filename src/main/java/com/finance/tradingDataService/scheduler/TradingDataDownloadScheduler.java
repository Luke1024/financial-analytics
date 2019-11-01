package com.finance.tradingDataService.scheduler;

import com.finance.tradingDataService.service.TradingDataDownloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TradingDataDownloadScheduler {
    @Autowired
    private TradingDataDownloaderService downloaderService;

    @Scheduled(cron = "0 0/10 * * * *")
    public void downloadTradingData() throws Exception{
        downloaderService.downloadCurrentTradingDataForStocks();
    }
}
