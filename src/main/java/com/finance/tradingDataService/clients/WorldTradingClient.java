package com.finance.tradingDataService.clients;

import com.finance.tradingDataService.config.WorldTradingApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class WorldTradingClient {

    @Autowired
    private WorldTradingApiConfig worldTradingApiConfig;

    private RestTemplate restTemplate = new RestTemplate();

    public String getCurrentUsdBasedCurrencyValues() throws Exception {
        URI url = UriComponentsBuilder.fromHttpUrl(
                worldTradingApiConfig.getBaseEndpoint() + worldTradingApiConfig.getUsdPoint() +
                worldTradingApiConfig.getKey())
                .build().encode().toUri();

        return restTemplate.getForObject(url, String.class);
    }

}
