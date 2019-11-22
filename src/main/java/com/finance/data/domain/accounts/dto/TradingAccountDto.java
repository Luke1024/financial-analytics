package com.finance.data.domain.accounts.dto;

import com.finance.data.domain.accounts.UserTradingAccountHistoryPoint;

import java.util.List;

public class TradingAccountDto {
    private double amount;
    private List<UserTradingAccountHistoryPointDto> points;

    public TradingAccountDto() {
    }

    public TradingAccountDto(double amount, List<UserTradingAccountHistoryPointDto> points) {
        this.amount = amount;
        this.points = points;
    }

    public double getAmount() {
        return amount;
    }

    public List<UserTradingAccountHistoryPointDto> getPoints() {
        return points;
    }
}
