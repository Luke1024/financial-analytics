package com.finance.data.domain.accounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class UserTradingAccountHistoryPoint {
    @Id
    @GeneratedValue
    private Long pointId;
    private OperationType operationType;
    private double accountChange;
    private double moneyAmountBeforeChange;
    private double moneyAmountAfterChange;
    private LocalDateTime localDateTime;
    private UserTradingAccount userTradingAccount;

    public UserTradingAccountHistoryPoint() {
    }

    public UserTradingAccountHistoryPoint(OperationType operationType, double accountChange, double moneyAmountBeforeChange, double moneyAmountAfterChange, LocalDateTime localDateTime, UserTradingAccount userTradingAccount) {
        this.operationType = operationType;
        this.accountChange = accountChange;
        this.moneyAmountBeforeChange = moneyAmountBeforeChange;
        this.moneyAmountAfterChange = moneyAmountAfterChange;
        this.localDateTime = localDateTime;
        this.userTradingAccount = userTradingAccount;
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

    public UserTradingAccount getUserTradingAccount() {
        return userTradingAccount;
    }
}
