package com.finance.data.domain.accounts;

import com.finance.data.domain.currency.Order;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserTradingAccountHistoryPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pointId;
    private OperationType operationType;
    private double accountChange;
    private double moneyAmountBeforeChange;
    private double moneyAmountAfterChange;
    private LocalDateTime localDateTime;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_TRADING_ACCOUNT_ID"
    )
    private UserTradingAccount userTradingAccount;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Order order;

    public UserTradingAccountHistoryPoint() {
    }

    public UserTradingAccountHistoryPoint(OperationType operationType, double accountChange,
                                          double moneyAmountBeforeChange, double moneyAmountAfterChange,
                                          LocalDateTime localDateTime, UserTradingAccount userTradingAccount, Order order) {
        this.operationType = operationType;
        this.accountChange = accountChange;
        this.moneyAmountBeforeChange = moneyAmountBeforeChange;
        this.moneyAmountAfterChange = moneyAmountAfterChange;
        this.localDateTime = localDateTime;
        this.userTradingAccount = userTradingAccount;
        this.order = order;
    }
}
