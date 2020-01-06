package com.finance.domain;

import com.finance.domain.enums.AccountType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//@NamedNativeQuery(
  //      name = "TradingAccount.findTradingAccountByUserId",
    //    query = "SELECT * FROM user_trading_account WHERE id =: ID",
      //  resultClass = TradingAccount.class
//)

@Entity
public class TradingAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;
    private AccountType accountType;
    private double amount;
    private int leverage;
    private LocalDateTime creationTime;
    private LocalDateTime archiveTime;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> openOrders;
    @OneToMany(targetEntity = TradingAccountHistoryPoint.class,
        mappedBy = "tradingAccount",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private List<TradingAccountHistoryPoint> points;

    public TradingAccount() {

    }

    public TradingAccount(Long id) {
        this.id = id;
    }

    public TradingAccount(User user, AccountType accountType, double amount, int leverage, LocalDateTime creationTime,
                          LocalDateTime archiveTime, List<Order> openOrders, List<TradingAccountHistoryPoint> points) {
        this.user = user;
        this.accountType = accountType;
        this.amount = amount;
        this.leverage = leverage;
        this.creationTime = creationTime;
        this.archiveTime = archiveTime;
        this.openOrders = openOrders;
        this.points = points;
    }

    public TradingAccount(Long id ,User user, AccountType accountType, double amount, int leverage, LocalDateTime creationTime,
                          LocalDateTime archiveTime, List<Order> openOrders, List<TradingAccountHistoryPoint> points) {
        this.id = id;
        this.user = user;
        this.accountType = accountType;
        this.amount = amount;
        this.leverage = leverage;
        this.creationTime = creationTime;
        this.archiveTime = archiveTime;
        this.openOrders = openOrders;
        this.points = points;
    }

    public List<Order> getOpenOrders() {
        return openOrders;
    }

    public void setLeverage(int leverage) {
        this.leverage = leverage;
    }

    public void setArchiveTime(LocalDateTime archiveTime) {
        this.archiveTime = archiveTime;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getAmount() {
        return amount;
    }

    public int getLeverage() {
        return leverage;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public List<TradingAccountHistoryPoint> getPoints() {
        return points;
    }
}
