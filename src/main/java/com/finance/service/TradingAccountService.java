package com.finance.service;

import com.finance.domain.User;
import com.finance.domain.TradingAccount;
import com.finance.domain.dto.TradingAccountLeverageModDto;
import com.finance.repository.TradingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TradingAccountService {
    @Autowired
    private TradingAccountRepository tradingAccountRepository;

    @Autowired
    private UserService userService;

    public List<TradingAccount> getAllTradingAccounts() {
        return tradingAccountRepository.findAll();
    }

    public List<TradingAccount> getUserTradingAccounts(Long userId) {
        return tradingAccountRepository.findTradingAccountByUserId(userId);
    }

    public TradingAccount modifyLeverageInAccount(
            TradingAccountLeverageModDto tradingAccountLeverageModDto) {
        User retrievedUser = userService.getUserById(tradingAccountLeverageModDto.getUserId());
        List<TradingAccount> retrievedAccount = retrievedUser.getTradingAccounts()
                .stream().filter(account -> account.getId()==tradingAccountLeverageModDto.getAccountId())
                .collect(Collectors.toList());
        if(! retrievedAccount.isEmpty()){
            TradingAccount tradingAccount = retrievedAccount.get(0);
            tradingAccount.setLeverage(tradingAccountLeverageModDto.getLeverage());
            return tradingAccountRepository.save(tradingAccount);
        } else {
            System.out.println("trading account not found");
            return new TradingAccount();
        }
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

    public void archiveAccount(Long accountId) {
        Optional<TradingAccount> retrievedTradingAccount = tradingAccountRepository.findById(accountId);
        if(retrievedTradingAccount.isPresent()){
            retrievedTradingAccount.get().setArchiveTime(LocalDateTime.now());
            tradingAccountRepository.save(retrievedTradingAccount.get());
        } else {
            System.out.println("Trading account not found.");
        }
    }
}
