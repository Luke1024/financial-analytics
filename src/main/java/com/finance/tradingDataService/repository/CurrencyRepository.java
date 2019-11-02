package com.finance.tradingDataService.repository;

import com.finance.tradingDataService.domain.Currency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    @Query
    List<Currency> findByCurrencyName(@Param("CURRENCY_NAME")String currency_name);

    void deleteById(Long id);

    @Override
    List<Currency> findAll();
}
