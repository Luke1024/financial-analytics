package com.finance.tradingDataService.repository;

import com.finance.tradingDataService.domain.Currency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    @Query
    List<Currency> retrieveByName(@Param("CURRENCY_NAME")String currency_name);
}
