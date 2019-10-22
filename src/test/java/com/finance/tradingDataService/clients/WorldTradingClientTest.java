package com.finance.tradingDataService.clients;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WorldTradingClientTest {
    @Autowired
    private WorldTradingClient worldTradingClient;

    @Test
    public void dtoObjectTest(){
        System.out.println(worldTradingClient.getCurrencyPairHistory());
    }
}