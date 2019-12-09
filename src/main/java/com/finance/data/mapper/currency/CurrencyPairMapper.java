package com.finance.data.mapper.currency;

import com.finance.data.domain.currency.CurrencyPairHistoryPoint;
import com.finance.data.domain.currency.dto.PairHistoryPointDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyPairMapper {
    public List<PairHistoryPointDto> mapToDTOList(List<CurrencyPairHistoryPoint> currencyPairHistoryPoints){
        return currencyPairHistoryPoints.stream().map(point -> new PairHistoryPointDto(point.getTimeStamp(),
                point.getValue())).collect(Collectors.toList());
    }
}
