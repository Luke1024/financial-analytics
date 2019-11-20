package com.finance.data.domain.accounts;

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
    private PersonalData personalData;
    private String password;
    private String email;
    @OneToMany(targetEntity = UserTradingAccount.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<UserTradingAccount> userTradingAccounts;
    private boolean userLoggedIn;
    private LocalDateTime registrationDate;

    public User() {}

    public User(String password, String email, List<UserTradingAccount> userTradingAccounts,
                boolean userLoggedIn, LocalDateTime registrationDate) {
        this.personalData = personalData;
        this.password = password;
        this.email = email;
        this.userTradingAccounts = userTradingAccounts;
        this.userLoggedIn = userLoggedIn;
        this.registrationDate = registrationDate;
    }

    public User(Long id) {
        this.id = id;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
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
