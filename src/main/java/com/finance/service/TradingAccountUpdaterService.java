package com.finance.service;

import com.finance.domain.TradingAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradingAccountUpdaterService {

    @Autowired
    private TradingAccountService tradingAccountService;

    public void updatePlatformService(){
        updateAllOpenOrders();
    }

    private void updateAllOpenOrders(){
        //List<TradingAccount> retrievedAccouts = retrievedTradingAccounts();
        //if(retrievedAccouts.size()>0){
          //  updateAccounts(retrievedAccouts);
        //}
    }

    private void updateAccounts(List<TradingAccount> retrievedAccounts){
        for(TradingAccount tradingAccount : retrievedAccounts){
            updateAccount(tradingAccount);
        }
    }

    private void updateAccount(TradingAccount tradingAccount){
        //tradingAccount.
        //for()
    }


/*
    private boolean isAccountTradable(TradingAccount tradingAccount){
        double amount = tradingAccount.getAmount();
        int leverage = tradingAccount.getLeverage();

        if(enoughMoney(tradingAccount)){ }
    }

    private boolean enoughMoney(TradingAccount tradingAccount){
        double amount = tradingAccount.getAmount();
        int leverage = tradingAccount.getLeverage();
        return leverage * amount >
    }
    */
}
