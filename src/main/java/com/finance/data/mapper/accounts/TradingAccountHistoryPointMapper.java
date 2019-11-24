package com.finance.data.mapper.accounts;

import com.finance.data.domain.accounts.TradingAccountHistoryPoint;
import com.finance.data.domain.accounts.dto.TradingAccountHistoryPointDto;
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
                        point.getMoneyAmountAfterChange(), point.getLocalDateTime(),
                        point.getTradingAccount().getId(), point.getOrder()
                        .getOrderId())).collect(Collectors.toList());
    }

    public List<TradingAccountHistoryPointDto> mapToTradingHistoryPointDtoList(List<TradingAccountHistoryPoint> historyPoints) {
        return historyPoints.stream().map(point -> new TradingAccountHistoryPointDto(point.getPointId(),
                point.getOperationType(),
                point.getAccountChange(),
                point.getMoneyAmountBeforeChange(),
                point.getMoneyAmountAfterChange(),
                point.getLocalDateTime(),
                point.getTradingAccount().getId(),
                point.getOrder().getOrderId())).collect(Collectors.toList());
    }
}
