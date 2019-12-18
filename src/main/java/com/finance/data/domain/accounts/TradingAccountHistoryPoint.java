package com.finance.data.domain.accounts;

import com.finance.data.domain.accounts.enums.OperationType;
import com.finance.data.domain.currency.Order;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TradingAccountHistoryPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pointId;
    private OperationType operationType;
    private double accountChange;
    private double moneyAmountBeforeChange;
    private double moneyAmountAfterChange;
    private LocalDateTime accountChangeTime;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_TRADING_ACCOUNT_ID")
    private TradingAccount tradingAccount;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Order order;

    public TradingAccountHistoryPoint() {
    }

    public TradingAccountHistoryPoint(Long pointId){
        this.pointId = pointId;
    }

    public TradingAccountHistoryPoint(OperationType operationType, double accountChange,
                                      double moneyAmountBeforeChange, double moneyAmountAfterChange,
                                      LocalDateTime accountChangeTime, TradingAccount tradingAccount, Order order) {
        this.operationType = operationType;
        this.accountChange = accountChange;
        this.moneyAmountBeforeChange = moneyAmountBeforeChange;
        this.moneyAmountAfterChange = moneyAmountAfterChange;
        this.accountChangeTime = accountChangeTime;
        this.tradingAccount = tradingAccount;
        this.order = order;
    }

    public TradingAccountHistoryPoint(Long pointId , OperationType operationType, double accountChange,
                                      double moneyAmountBeforeChange, double moneyAmountAfterChange,
                                      LocalDateTime accountChangeTime, TradingAccount tradingAccount, Order order) {
        this.pointId = pointId;
        this.operationType = operationType;
        this.accountChange = accountChange;
        this.moneyAmountBeforeChange = moneyAmountBeforeChange;
        this.moneyAmountAfterChange = moneyAmountAfterChange;
        this.accountChangeTime = accountChangeTime;
        this.tradingAccount = tradingAccount;
        this.order = order;
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

    public LocalDateTime getAccountChangeTime() {
        return accountChangeTime;
    }

    public TradingAccount getTradingAccount() {
        return tradingAccount;
    }

    public Order getOrder() {
        return order;
    }
}
