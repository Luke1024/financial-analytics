package com.finance.data.repository.currency;

import com.finance.data.domain.currency.CurrencyPair;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends CrudRepository<CurrencyPair, Long> {
    @Query
    List<CurrencyPair> retrieveByBaseAndName(@Param("BASE")String base, @Param("CURRENCY_NAME")String currency_name);

    List<CurrencyPair> findByCurrencyName(String currencyName);

    List<CurrencyPair> findByBase(String baseName);

    void deleteById(Long id);

    @Override
    List<CurrencyPair> findAll();
}
