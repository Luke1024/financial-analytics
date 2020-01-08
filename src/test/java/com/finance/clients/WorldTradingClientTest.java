package com.finance.clients;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.json.*;

@SpringBootTest
public class WorldTradingClientTest {
    @Autowired
    private WorldTradingClient worldTradingClient;

    @Test
    public void getUsdBasedCurrencyValues() throws Exception {

        String result = worldTradingClient.getCurrentUsdBasedCurrencyValues();
        JSONObject obj = new JSONObject(result);
        JSONObject objData = obj.getJSONObject("data");

        objData.keySet().stream().forEach(c -> System.out.println(c));
        //objData.keySet().stream().forEach(k -> System.out.println(k + " " + objData.get(k)));

        //Arrays.stream(currencies).forEach(c -> System.out.println(c));
    }
}