package com.finance.data.mapper.accounts;

import com.finance.data.domain.accounts.UserTradingAccountHistoryPoint;
import com.finance.data.domain.accounts.dto.UserTradingAccountHistoryPointDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserTradingAccountHistoryPointMapper {
    public List<UserTradingAccountHistoryPointDto> mapToTradingHistoryPointList(List<UserTradingAccountHistoryPoint> historyPoints) {
        return historyPoints.stream().map(point -> new UserTradingAccountHistoryPointDto(point.getPointId(),
                point.getOperationType(),
                point.getAccountChange(),
                point.getMoneyAmountBeforeChange(),
                point.getMoneyAmountAfterChange(),
                point.getLocalDateTime(),
                point.getUserTradingAccount().getId(),
                point.getOrder().getOrderId())).collect(Collectors.toList());
    }
}
