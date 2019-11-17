package com.finance.data.domain.accounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class UserTradingAccount {
    @Id
    @GeneratedValue
    private Long id;
    private User user;
    private double amount;
    private int leverage;
    private LocalDateTime openingTime;
    private List<UserTradingAccountHistoryPoint> points;

    public UserTradingAccount() {
    }

    public UserTradingAccount(User user, double amount, int leverage, LocalDateTime openingTime, List<UserTradingAccountHistoryPoint> points) {
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
