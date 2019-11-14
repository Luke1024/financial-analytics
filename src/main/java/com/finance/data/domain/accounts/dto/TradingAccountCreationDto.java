package com.finance.data.domain.accounts.dto;

public class TradingAccountCreationDto {
    private Long userId;
    private AccountType accountType;

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
}
