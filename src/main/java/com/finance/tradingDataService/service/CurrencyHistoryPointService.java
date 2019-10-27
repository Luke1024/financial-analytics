package com.finance.tradingDataService.service;

import com.finance.tradingDataService.repository.CurrencyHistoryPointRepository;
import com.finance.tradingDataService.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyHistoryPointService {
    @Autowired
    private CurrencyHistoryPointRepository currencyHistoryPointRepository;

    @Autowired
    private CurrencyRepository currencyRepository;


}
