package com.finance.repository;

import com.finance.domain.CurrencyPairDataPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CurrencyPairHistoryPointRepository extends CrudRepository<CurrencyPairDataPoint, Long> {
    /* @Query
    List<CurrencyPairDataPoint> retrieveByTimeRangeAndCurrencyName(@Param("TIME_STAMP_START")LocalDateTime time_stamp_start,
                                                                   @Param("TIME_STAMP_STOP")LocalDateTime time_stamp_stop,
                                                                   @Param("CURRENCY_ID")String currencyName); */

    Optional<CurrencyPairDataPoint> getLastDataPoint(long pair_id);

    Optional<CurrencyPairDataPoint> findPointByDate(@Param("TIME_STAMP")LocalDateTime time_stamp,
                                          @Param("PAIR_ID") long pair_id);

    @Override
    CurrencyPairDataPoint save(CurrencyPairDataPoint currencyPairDataPoint);
}