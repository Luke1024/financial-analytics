package com.finance.data.service.account;

import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.UserTradingAccount;
import com.finance.data.repository.accounts.TradingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradingAccountService {
    @Autowired
    private TradingAccountRepository tradingAccountRepository;

    @Autowired
    private UserService userService;

    public List<UserTradingAccount> getUserTradingAccounts(Long userId) {
        return tradingAccountRepository.findTradingAccountByUserId(userId);
    }

    public void createTradingAccount(UserTradingAccount userTradingAccount) {
        User retrievedUser = userService.getUserById(userTradingAccount.getUser().getId());
        if(retrievedUser != null){
            tradingAccountRepository.save(userTradingAccount);
        } else {
            System.out.println("User not found");
        }
    }

    public void updateTradingAccount()
}
