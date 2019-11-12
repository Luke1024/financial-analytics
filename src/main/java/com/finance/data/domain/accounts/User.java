package com.finance.data.domain.accounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private UserAdress userAdress;
    private String password;
    private String email;
    private List<UserTradingAccount> userTradingAccounts;
    private List<BankAccount> bankAccounts;

    public User() {
    }

    public User(UserAdress userAdress, String password, String email, List<UserTradingAccount> userTradingAccounts, List<BankAccount> bankAccounts) {
        this.userAdress = userAdress;
        this.password = password;
        this.email = email;
        this.userTradingAccounts = userTradingAccounts;
        this.bankAccounts = bankAccounts;
    }

    public Long getId() {
        return id;
    }

    public UserAdress getUserAdress() {
        return userAdress;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<UserTradingAccount> getUserTradingAccounts() {
        return userTradingAccounts;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
