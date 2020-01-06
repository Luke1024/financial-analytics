package com.finance.mapper;

import com.finance.domain.CurrencyPair;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyMapper {

    public List<String> mapToStringKeys(List<CurrencyPair> currencies){
        return currencies.stream().map(c -> c.getCurrencyPairName()).collect(Collectors.toList());
    }
}
