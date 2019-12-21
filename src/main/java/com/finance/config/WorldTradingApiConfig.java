package com.finance.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WorldTradingApiConfig {

    @Value("rceeWn77JLTDJHpqYZzkivU1gj1c1gOrnZ3nvNkoxJE4ihD3Ck7ts2fYEgW6")
    private String key;

    @Value("https://api.worldtradingdata.com/api/v1/")
    private String baseEndpoint;

    @Value("forex?base=USD&api_token=")
    private String usdPoint;

    public String getKey() {
        return key;
    }

    public String getBaseEndpoint() {
        return baseEndpoint;
    }

    public String getUsdPoint() {
        return usdPoint;
    }
}
