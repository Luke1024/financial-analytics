package com.finance.repository;

import com.finance.domain.CurrencyPair;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyPairRepository extends CrudRepository<CurrencyPair, Long> {
    //@Query
    //List<CurrencyPairRepository> retrieveByBaseAndName(@Param("BASE")String base, @Param("CURRENCY_NAME")String currency_name);

    @Query
    List<CurrencyPair> findByCurrencyName(@Param("CURRENCY_NAME") String currencyName);

    void deleteById(Long id);

    @Override
    List<CurrencyPair> findAll();
}
