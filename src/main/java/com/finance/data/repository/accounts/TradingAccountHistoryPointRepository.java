package com.finance.data.repository.accounts;

import com.finance.data.domain.accounts.TradingAccountHistoryPoint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradingAccountHistoryPointRepository extends CrudRepository<TradingAccountHistoryPoint, Long> {

    List<TradingAccountHistoryPoint> findByTradingAccountId(Long id);

}
