package com.finance.data.service.account;

import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.TradingAccount;
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

    public List<TradingAccount> getUserTradingAccounts(Long userId) {
        return tradingAccountRepository.findTradingAccountByUserId(userId);
    }

    public void createTradingAccount(TradingAccount tradingAccount) {
        User retrievedUser = userService.getUserById(tradingAccount.getUser().getId());
        if(retrievedUser != null){
            retrievedUser.getTradingAccounts().add(tradingAccount);
            userService.saveUser(retrievedUser);
        } else {
            System.out.println("User not found");
        }
    }
}
