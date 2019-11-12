package com.finance.data.mapper;

import com.finance.data.domain.Currency;
import com.finance.data.service.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyMapper {
    @Autowired
    private CurrencyService currencyService;

    public List<String> mapToStringKeys(List<Currency> currencies){
        return currencyService.getCurrencies().stream().map(c -> c.getCurrencyName()).collect(Collectors.toList());
    }
}
