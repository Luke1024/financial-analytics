package com.finance.mapper;

import com.finance.domain.TradingAccountHistoryPoint;
import com.finance.domain.dto.TradingAccountHistoryPointDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TradingAccountHistoryPointMapper {
    public List<TradingAccountHistoryPointDto> mapToAccountHistoryPointDtos
            (List<TradingAccountHistoryPoint> tradingAccountHistoryPoints){
        return tradingAccountHistoryPoints.stream()
                .map(point -> new TradingAccountHistoryPointDto(point.getPointId(),
                        point.getOperationType(), point.getAccountChange(), point.getMoneyAmountBeforeChange(),
                        point.getMoneyAmountAfterChange(), point.getAccountChangeTime(),
                        point.getTradingAccount().getId(), point.getOrder()
                        .getOrderId())).collect(Collectors.toList());
    }
}
