package com.finance.tradingDataService.mapper;

import com.finance.tradingDataService.domain.dto.CurrencyPairStatusDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyPairMapper {
    public List<CurrencyPairStatusDTO> mapToDTOList(List<CurrencyPairStatusDTO> currencyPairStatusDTOS){
        return currencyPairStatusDTOS.stream()
                .map(c -> new CurrencyPairStatusDTO(c.getCurrencyPairName(), c.getValue()))
                .collect(Collectors.toList());
    }
}
