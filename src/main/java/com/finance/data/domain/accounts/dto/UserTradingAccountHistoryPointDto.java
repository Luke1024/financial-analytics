package com.finance.data.domain.accounts.dto;

import com.finance.data.domain.accounts.OperationType;

public class UserTradingAccountHistoryPointDto {
    private Long pointId;
    private OperationType operationType;
    private double accountChange;
    private double moneyAmountBeforeChange;
    private double moneyAmountAfterChange;

    public UserTradingAccountHistoryPointDto() {
    }

    public UserTradingAccountHistoryPointDto(Long pointId, OperationType operationType, double accountChange,
                                             double moneyAmountBeforeChange, double moneyAmountAfterChange) {
        this.pointId = pointId;
        this.operationType = operationType;
        this.accountChange = accountChange;
        this.moneyAmountBeforeChange = moneyAmountBeforeChange;
        this.moneyAmountAfterChange = moneyAmountAfterChange;
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
}
