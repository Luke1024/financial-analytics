package com.finance.scheduler;

import com.finance.service.TradingAccountUpdaterService;
import com.finance.service.TradingDataDownloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class TradingPlatformUpdaterScheduler {
    @Autowired
    private TradingDataDownloaderService downloaderService;

    @Autowired
    private TradingAccountUpdaterService tradingAccountUpdaterService;

    //@Scheduled(cron = "0 0/10 * * * *")
    //public void downloadTradingData() throws Exception{
      //  LocalDateTime currentDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        //downloaderService.downloadCurrentTradingDataForStocks(currentDateTime);
        //tradingAccountUpdaterService.updatePlatformService();
    //}
}
