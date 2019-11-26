package com.finance.data.repository.currency;

import com.finance.data.domain.currency.Currency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    @Query
    List<Currency> retrieveByBaseAndName(@Param("BASE")String base, @Param("CURRENCY_NAME")String currency_name);

    List<Currency> findByCurrencyName(String currencyName);

    List<Currency> findByBase(String baseName);

    void deleteById(Long id);

    @Override
    List<Currency> findAll();
}
