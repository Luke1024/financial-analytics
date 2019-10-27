package com.finance.tradingDataService.service;

import com.finance.tradingDataService.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyHistoryPointService currencyHistoryPointService;

    @Autowired
    private CurrencyRepository currencyRepository;

    public
}
