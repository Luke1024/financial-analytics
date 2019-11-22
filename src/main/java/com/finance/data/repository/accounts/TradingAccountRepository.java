package com.finance.data.repository.accounts;

import com.finance.data.domain.accounts.UserTradingAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradingAccountRepository extends CrudRepository<UserTradingAccount, Long> {

    @Query
    List<UserTradingAccount> findTradingAccountByUserId(Long id);
}
