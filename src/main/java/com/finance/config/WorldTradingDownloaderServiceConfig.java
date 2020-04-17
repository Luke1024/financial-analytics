package com.finance.config;

import org.springframework.stereotype.Component;

@Component
public class WorldTradingDownloaderServiceConfig {
    private final static String[] currencyPairs =
            {"EUR/USD",
                    "USD/JPY",
                    "GBP/USD",
                    "AUD/USD",
                    "USD/CHF",
                    "USD/CAD"
            };

    private final static String baseCurrency = "USD";

    public String[] getCurrencyPairs() {
        return currencyPairs;
    }

    public String getApiSetBaseCurrency() {
        return baseCurrency;
    }
}
