package com.finance.tradingDataService.service;

import com.finance.tradingDataService.clients.WorldTradingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
public class TradingDataDownloader {

    @Autowired
    private WorldTradingClient worldTradingClient;

    private void downloadCurrentTradingDataForStocks() throws Exception {
    }
}
