package com.finance.mapper;

import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.dto.currencyPair.DataPointDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyPairDataPointMapper {

    public List<DataPointDto> mapToDataPoints(List<CurrencyPairDataPoint> currencyPairDataPoints) {
        return currencyPairDataPoints.stream().map(point -> new DataPointDto(point.getTimeStamp(), point.getValue()))
                .collect(Collectors.toList());
    }
}