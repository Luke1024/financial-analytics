package com.finance.data.domain.accounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue
    private Long bankAccountId;
    private UserAdress personalData;
    private String bankName;
    private String bankNumber;
    private User user;

    public BankAccount() {
    }

    public BankAccount(Long bankAccountId, UserAdress personalData, String bankName, String bankNumber, User user) {
        this.bankAccountId = bankAccountId;
        this.personalData = personalData;
        this.bankName = bankName;
        this.bankNumber = bankNumber;
        this.user = user;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public UserAdress getPersonalData() {
        return personalData;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public User getUser() {
        return user;
    }
}
