package com.finance.mapper;

import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairHistoryPoint;
import com.finance.domain.dto.currencyPair.CurrencyPairDataDto;
import com.finance.domain.dto.currencyPair.DataPointDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CurrencyPairMapper {
    public List<String> mapToPairsStringList(List<CurrencyPair> currencyPairs){
        return currencyPairs.stream().map(currencyPair -> currencyPair.getCurrencyPairName())
                .collect(Collectors.toList());
    }

    public CurrencyPairDataDto mapToOverviewDto(Optional<CurrencyPair> currencyPair){
        if(currencyPair.isPresent()) {
            return new CurrencyPairDataDto(currencyPair.get().getCurrencyPairName(), LocalDateTime.now(),
                    mapHistoryPointsToDataPointDto(currencyPair.get().getCurrencyPairHistoryPoints()));
        } else {
            return new CurrencyPairDataDto();
        }
    }

    private List<DataPointDto> mapHistoryPointsToDataPointDto(List<CurrencyPairHistoryPoint> currencyPairHistoryPoints){
        return currencyPairHistoryPoints.stream().map(point -> new DataPointDto(point.getTimeStamp(), point.getValue()))
                .collect(Collectors.toList());
    }

    /*
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

     */
}
