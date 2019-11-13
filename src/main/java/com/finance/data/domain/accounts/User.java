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
    private PersonalData personalData;
    private String password;
    private String email;
    private List<UserTradingAccount> userTradingAccounts;

    public User() {
    }

    public User(PersonalData personalData, String password, String email, List<UserTradingAccount> userTradingAccounts) {
        this.personalData = personalData;
        this.password = password;
        this.email = email;
        this.userTradingAccounts = userTradingAccounts;
    }

    public Long getId() {
        return id;
    }

    public PersonalData getPersonalData() {
        return personalData;
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
}
