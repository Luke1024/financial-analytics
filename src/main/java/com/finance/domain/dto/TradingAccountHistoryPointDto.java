package com.finance.domain.dto;

import com.finance.domain.enums.OperationType;

import java.time.LocalDateTime;

public class TradingAccountHistoryPointDto {
    private Long pointId;
    private OperationType operationType;
    private double accountChange;
    private double moneyAmountBeforeChange;
    private double moneyAmountAfterChange;
    private LocalDateTime localDateTime;
    private Long userTradingAccountId;
    private Long orderId;

    public TradingAccountHistoryPointDto() {
    }

    public TradingAccountHistoryPointDto(Long pointId, OperationType operationType, double accountChange,
                                         double moneyAmountBeforeChange, double moneyAmountAfterChange,
                                         LocalDateTime localDateTime, Long userTradingAccountId, Long orderId) {
        this.pointId = pointId;
        this.operationType = operationType;
        this.accountChange = accountChange;
        this.moneyAmountBeforeChange = moneyAmountBeforeChange;
        this.moneyAmountAfterChange = moneyAmountAfterChange;
        this.localDateTime = localDateTime;
        this.userTradingAccountId = userTradingAccountId;
        this.orderId = orderId;
    }

    public Long getPointId() {
        return pointId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public double getAccountChange() {
        return accountChange;
    }

    public double getMoneyAmountBeforeChange() {
        return moneyAmountBeforeChange;
    }

    public double getMoneyAmountAfterChange() {
        return moneyAmountAfterChange;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Long getUserTradingAccountId() {
        return userTradingAccountId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
