package com.finance.data.repository.currency;

import com.finance.data.domain.currency.CurrencyPair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyPairRepository extends CrudRepository<CurrencyPair, Long> {
    //@Query
    //List<CurrencyPairRepository> retrieveByBaseAndName(@Param("BASE")String base, @Param("CURRENCY_NAME")String currency_name);

    Optional<CurrencyPair> findByCurrencyName(String currencyName);

    void deleteById(Long id);

    @Override
    List<CurrencyPair> findAll();
}
