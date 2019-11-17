package com.finance.data.service.account;

import com.finance.data.domain.accounts.UserTradingAccount;
import com.finance.data.domain.accounts.dto.TradingAccountCreationDto;
import com.finance.data.mapper.accounts.TradingAccountMapper;
import com.finance.data.repository.accounts.TradingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradingAccountService {
    @Autowired
    private TradingAccountRepository tradingAccountRepository;

    @Autowired
    private TradingAccountMapper tradingAccountMapper;

    //public List<UserTradingAccount> getUserTradingAccounts(Long userId) {
      //  return tradingAccountRepository.findTradingAccountByUser(userId);
    //}

    //public void createTradingAccount(TradingAccountCreationDto tradingAccountCreationDto) {
      //  tradingAccountRepository.save(tradingAccountMapper.mapToNewTradingAccount(tradingAccountCreationDto));
    //}
}
