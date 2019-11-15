package com.finance.data.repository.accounts;

import com.finance.data.domain.accounts.UserTradingAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradingAccountRepository extends CrudRepository<UserTradingAccount, Long> {

}
