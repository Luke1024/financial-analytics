package com.finance.data.domain.accounts.dto;

public class AccountDeletionDto {
    private Long userId;
    private Long accountId;
    private String bankAccountToTransfer;

    public AccountDeletionDto(Long userId, Long accountId, String bankAccountToTransfer) {
        this.userId = userId;
        this.accountId = accountId;
        this.bankAccountToTransfer = bankAccountToTransfer;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getBankAccountToTransfer() {
        return bankAccountToTransfer;
    }
}
