package com.finance.data.domain.accounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class UserTradingAccount {
    @Id
    @GeneratedValue
    private Long id;
    private User user;
    private double amount;
    private List<UserTradingAccountHistoryPoint> points;

    public UserTradingAccount() {
    }

    public UserTradingAccount(User user, double amount, List<UserTradingAccountHistoryPoint> points) {
        this.user = user;
        this.amount = amount;
        this.points = points;
    }

    public UserTradingAccount(Long id, User user, double amount, List<UserTradingAccountHistoryPoint> points) {
        this.id = id;
        this.user = user;
        this.amount = amount;
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

    public List<UserTradingAccountHistoryPoint> getPoints() {
        return points;
    }
}
