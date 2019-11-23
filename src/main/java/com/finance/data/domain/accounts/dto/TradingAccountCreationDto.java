package com.finance.data.domain.accounts.dto;

import com.finance.data.domain.accounts.AccountType;

public class TradingAccountCreationDto {
    private Long userId;
    private AccountType accountType;
    private int leverage;

    public TradingAccountCreationDto(Long userId, AccountType accountType) {
        this.userId = userId;
        this.accountType = accountType;
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
