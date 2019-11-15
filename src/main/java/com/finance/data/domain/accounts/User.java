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
    private UserStatus userStatus;

    public User() {}

    public User(PersonalData personalData, String password, String email, List<UserTradingAccount> userTradingAccounts, UserStatus userStatus) {
        this.personalData = personalData;
        this.password = password;
        this.email = email;
        this.userTradingAccounts = userTradingAccounts;
        this.userStatus = userStatus;
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

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
