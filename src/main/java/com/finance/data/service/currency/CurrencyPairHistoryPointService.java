package com.finance.data.service.currency.utilities;

import com.finance.data.repository.currency.CurrencyPairHistoryPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyHistoryPointService {

    @Autowired
    private CurrencyPairHistoryPointRepository repository;



}
