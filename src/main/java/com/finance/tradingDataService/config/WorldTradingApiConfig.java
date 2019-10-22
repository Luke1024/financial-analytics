package com.finance.tradingDataService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WorldTradingApiConfig {

    @Value("rceeWn77JLTDJHpqYZzkivU1gj1c1gOrnZ3nvNkoxJE4ihD3Ck7ts2fYEgW6")
    private String key;

    @Value("https://api.worldtradingdata.com/api/v1/")
    private String baseEndpoint;

    @Value("forex_history?base=EUR&convert_to=USD&sort=newest&api_token=")
    private String eurUsd;

    public String getKey() {
        return key;
    }

    public String getBaseEndpoint() {
        return baseEndpoint;
    }

    public String getEurUsd() {
        return eurUsd;
    }
}
