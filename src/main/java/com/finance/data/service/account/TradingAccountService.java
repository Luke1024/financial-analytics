package com.finance.data.service.account;

import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.UserTradingAccount;
import com.finance.data.repository.accounts.TradingAccountRepository;
import com.finance.data.repository.accounts.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradingAccountService {
    @Autowired
    private TradingAccountRepository tradingAccountRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public List<UserTradingAccount> getUserTradingAccounts(Long userId) {
        return tradingAccountRepository.findTradingAccountByUserId(userId);
    }

    public void createTradingAccount(UserTradingAccount userTradingAccount) {
        User retrievedUser = userService.getUserById(userTradingAccount.getUser().getId());
        if(retrievedUser != null){
            retrievedUser.getUserTradingAccounts().add(userTradingAccount);
            userService.saveUser(retrievedUser);
        } else {
            System.out.println("User not found");
        }
    }
}
