package com.finance.data.domain.accounts.dto;

import com.finance.data.domain.accounts.AccountType;

public class TradingAccountCreationDto {
    private Long userId;
    private AccountType accountType;
    private int leverage;

    public TradingAccountCreationDto() {
    }

    public TradingAccountCreationDto(Long userId, AccountType accountType, int leverage) {
        this.userId = userId;
        this.accountType = accountType;
        this.leverage = leverage;
    }

    public Long getUserId() {
        return userId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public int getLeverage() {
        return leverage;
    }
}
