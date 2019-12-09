package com.finance.data.mapper.currency;

import com.finance.data.domain.currency.CurrencyPair;
import com.finance.data.service.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyMapper {
    @Autowired
    private CurrencyService currencyService;

    public List<String> mapToStringKeys(List<CurrencyPair> currencies){
        return currencies.stream().map(c -> c.getCurrencyPairName()).collect(Collectors.toList());
    }
}
