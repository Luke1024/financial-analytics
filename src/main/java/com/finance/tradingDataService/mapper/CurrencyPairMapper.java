package com.finance.tradingDataService.mapper;

import com.finance.tradingDataService.domain.CurrencyHistoryPoint;
import com.finance.tradingDataService.domain.dto.PairHistoryPointDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyPairMapper {
    public List<PairHistoryPointDto> mapToDTOList(List<CurrencyHistoryPoint> currencyHistoryPoints){
        return currencyHistoryPoints.stream().map(point -> new PairHistoryPointDto(point.getTimeStamp(),
                point.getValue())).collect(Collectors.toList());
    }
}
