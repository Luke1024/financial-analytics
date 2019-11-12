package com.finance.data.scheduler;

import com.finance.data.service.datadownloader.TradingDataDownloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class TradingDataDownloadScheduler {
    @Autowired
    private TradingDataDownloaderService downloaderService;

    @Scheduled(cron = "0 0/10 * * * *")
    public void downloadTradingData() throws Exception{
        LocalDateTime currentDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        downloaderService.downloadCurrentTradingDataForStocks(currentDateTime);
    }
}
