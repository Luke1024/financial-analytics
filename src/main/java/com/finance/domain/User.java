package com.finance.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedNativeQuery(
        name = "User.findUserByEmail",
        query = "SELECT * FROM user WHERE email = :EMAIL",
        resultClass = User.class
)

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserData userData;
    private String password;
    private String email;
    @OneToMany(targetEntity = TradingAccount.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<TradingAccount> tradingAccounts;
    private boolean userLoggedIn;
    private LocalDateTime registrationDate;

    public User() {}

    public User(String password, String email, List<TradingAccount> tradingAccounts,
                boolean userLoggedIn, LocalDateTime registrationDate) {
        this.password = password;
        this.email = email;
        this.tradingAccounts = tradingAccounts;
        this.userLoggedIn = userLoggedIn;
        this.registrationDate = registrationDate;
    }

    public User(UserData userData, String password, String email, List<TradingAccount> tradingAccounts,
                boolean userLoggedIn, LocalDateTime registrationDate) {
        this.userData = userData;
        this.password = password;
        this.email = email;
        this.tradingAccounts = tradingAccounts;
        this.userLoggedIn = userLoggedIn;
        this.registrationDate = registrationDate;
    }

    public User(Long id) {
        this.id = id;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Long getId() {
        return id;
    }

    public UserData getUserData() {
        return userData;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public List<TradingAccount> getTradingAccounts() {
        return tradingAccounts;
    }

    public boolean getUserLoggedIn() {
        return userLoggedIn;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setUserLoggedIn(boolean userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }
}
