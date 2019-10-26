package com.finance.tradingDataService.repository;

import com.finance.tradingDataService.domain.CurrencyHistoryPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyHistoryPointRepository extends CrudRepository<CurrencyHistoryPoint, Long> {
}
