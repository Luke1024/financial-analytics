package com.finance.data.domain.accounts.dto;

import com.finance.data.domain.accounts.UserTradingAccountHistoryPoint;

import java.util.List;

public class TradingAccountDto {
    private double amount;
    private List<UserTradingAccountHistoryPoint> points;

    public TradingAccountDto(double amount, List<UserTradingAccountHistoryPoint> points) {
        this.amount = amount;
        this.points = points;
    }

    public double getAmount() {
        return amount;
    }

    public List<UserTradingAccountHistoryPoint> getPoints() {
        return points;
    }
}
