package com.finance.service.database.pairdatapointserviceutilities;

import org.springframework.stereotype.Service;

@Service
public class CurrencyPairDataPointCache {
    //how to map currencyPair?
    private boolean loaded = false;

    public boolean isLoaded() {
        return loaded;
    }

    public boolean load(){

    }
}
