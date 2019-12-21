package com.finance.domain.dto;

public class TradingAccountLeverageModDto {
    private Long userId;
    private Long accountId;
    private int leverage;

    public TradingAccountLeverageModDto(Long userId, Long accountId, int leverage) {
        this.userId = userId;
        this.accountId = accountId;
        this.leverage = leverage;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public int getLeverage() {
        return leverage;
    }
}
