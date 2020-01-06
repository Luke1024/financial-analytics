package com.finance.repository;

import com.finance.domain.TradingAccountHistoryPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradingAccountHistoryPointRepository extends CrudRepository<TradingAccountHistoryPoint, Long> {

    List<TradingAccountHistoryPoint> findByTradingAccountId(Long id);

}
