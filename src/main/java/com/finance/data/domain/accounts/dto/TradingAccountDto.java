package com.finance.data.domain.accounts.dto;

import com.finance.data.domain.accounts.enums.AccountType;

import java.time.LocalDateTime;
import java.util.List;

public class TradingAccountDto {
    private Long id;
    private Long userId;
    private AccountType accountType;
    private double amount;
    private double leverage;
    private LocalDateTime openingTime;
    private List<TradingAccountHistoryPointDto> points;

    public TradingAccountDto() {
    }

    public TradingAccountDto(Long id, Long userId, AccountType accountType, double amount, double leverage, LocalDateTime openingTime, List<TradingAccountHistoryPointDto> points) {
        this.id = id;
        this.userId = userId;
        this.accountType = accountType;
        this.amount = amount;
        this.leverage = leverage;
        this.openingTime = openingTime;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getAmount() {
        return amount;
    }

    public double getLeverage() {
        return leverage;
    }

    public LocalDateTime getOpeningTime() {
        return openingTime;
    }

    public List<TradingAccountHistoryPointDto> getPoints() {
        return points;
    }
}
