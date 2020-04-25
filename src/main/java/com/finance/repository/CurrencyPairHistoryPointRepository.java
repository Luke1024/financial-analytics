package com.finance.repository;

import com.finance.domain.CurrencyPairDataPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyPairHistoryPointRepository extends CrudRepository<CurrencyPairDataPoint, Long> {
    /* @Query
    List<CurrencyPairDataPoint> retrieveByTimeRangeAndCurrencyName(@Param("TIME_STAMP_START")LocalDateTime time_stamp_start,
                                                                   @Param("TIME_STAMP_STOP")LocalDateTime time_stamp_stop,
                                                                   @Param("CURRENCY_ID")String currencyName); */

    //CurrencyPairDataPoint getLastDataPoint();

    //CurrencyPairDataPoint findPointByDate();

    @Override
    CurrencyPairDataPoint save(CurrencyPairDataPoint currencyPairDataPoint);
}