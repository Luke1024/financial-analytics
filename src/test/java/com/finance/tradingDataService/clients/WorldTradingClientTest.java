package com.finance.tradingDataService.clients;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.json.*;

import java.util.Arrays;

@SpringBootTest
public class WorldTradingClientTest {
    @Autowired
    private WorldTradingClient worldTradingClient;

    @Test
    void getUsdBasedCurrencyValues() throws Exception {

        String result = worldTradingClient.getCurrentUsdBasedCurrencyValues();
        JSONObject obj = new JSONObject(result);
        JSONObject objData = obj.getJSONObject("data");

        String[] currencies = JSONObject.getNames(objData);
        objData.keySet().stream().forEach(c -> System.out.println(c));

        //Arrays.stream(currencies).forEach(c -> System.out.println(c));
    }
}