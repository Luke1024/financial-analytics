package com.finance.config;

import org.springframework.stereotype.Component;

@Component
public class WorldTradingDownloaderServiceConfig {
    private final String[] currencyPairs =
            {"USD/CAD",
                    "EUR/JPY",
                    "EUR/USD",
                    "EUR/CHF",
                    "USD/CHF",
                    "EUR/GBP",
                    "GBP/USD",
                    "AUD/CAD",
                    "NZD/USD",
                    "GBP/CHF",
                    "AUD/USD",
                    "GBP/JPY",
                    "USD/JPY",
                    "CHF/JPY",
                    "EUR/CAD",
                    "AUD/JPY",
                    "EUR/AUD",
                    "AUD/NZD"
            };

    private final String apiSetBaseCurrency = "USD";

    public String[] getCurrencyPairs() {
        return currencyPairs;
    }

    public String getApiSetBaseCurrency() {
        return apiSetBaseCurrency;
    }
}
