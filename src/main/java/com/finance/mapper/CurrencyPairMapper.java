package com.finance.mapper;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairHistoryPoint;
import com.finance.domain.dto.CurrencyOverviewDto;
import com.finance.domain.dto.PairHistoryPointDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyPairMapper {
    public List<CurrencyOverviewDto> mapToOverviewDto(List<CurrencyPair> currencyPairs){
        return currencyPairs.stream().map(currencyPair ->
                new CurrencyOverviewDto(currencyPair.getCurrencyPairName(),
                        currencyPair.getLastPairHistoryPoint().getValue()))
                .collect(Collectors.toList());
    }

    public List<PairHistoryPointDto> mapToDTOList(List<CurrencyPairHistoryPoint> currencyPairHistoryPoints){
        return currencyPairHistoryPoints.stream().map(point -> new PairHistoryPointDto(point.getTimeStamp(),
                point.getValue())).collect(Collectors.toList());
    }
}
