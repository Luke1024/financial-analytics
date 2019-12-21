package com.finance.repository;

import com.finance.domain.TradingAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradingAccountRepository extends CrudRepository<TradingAccount, Long> {

    @Query
    List<TradingAccount> findTradingAccountByUserId(Long id);

    @Override
    TradingAccount save(TradingAccount tradingAccount);
}
