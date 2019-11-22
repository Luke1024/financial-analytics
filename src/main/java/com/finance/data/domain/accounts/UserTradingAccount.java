package com.finance.data.domain.accounts;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//@NamedNativeQuery(
  //      name = "UserTradingAccount.findTradingAccountByUserId",
    //    query = "SELECT * FROM user_trading_account WHERE id =: ID",
      //  resultClass = UserTradingAccount.class
//)

@Entity
public class UserTradingAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;
    private double amount;
    private int leverage;
    private LocalDateTime openingTime;
    @OneToMany(targetEntity = UserTradingAccountHistoryPoint.class,
        mappedBy = "userTradingAccount",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private List<UserTradingAccountHistoryPoint> points;

    public UserTradingAccount() {
    }

    public UserTradingAccount(User user, double amount, int leverage, LocalDateTime openingTime,
                              List<UserTradingAccountHistoryPoint> points) {
        this.user = user;
        this.amount = amount;
        this.leverage = leverage;
        this.openingTime = openingTime;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    public int getLeverage() {
        return leverage;
    }

    public LocalDateTime getOpeningTime() {
        return openingTime;
    }

    public List<UserTradingAccountHistoryPoint> getPoints() {
        return points;
    }
}
