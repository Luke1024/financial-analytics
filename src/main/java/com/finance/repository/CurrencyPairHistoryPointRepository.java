package com.finance.repository;

import com.finance.domain.CurrencyPairDataPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyPairHistoryPointRepository extends CrudRepository<CurrencyPairDataPoint, Long> {
    List<CurrencyPairDataPoint> retrieveByTimeRangeAndCurrencyName(@Param("PAIR_ID")Long pairId,
                                                                   @Param("TIME_STAMP_START")LocalDateTime time_stamp_start,
                                                                   @Param("TIME_STAMP_STOP")LocalDateTime time_stamp_stop);

    Optional<CurrencyPairDataPoint> getLastDataPoint(@Param("PAIR_ID")long pair_id);

    Optional<CurrencyPairDataPoint> findPointByDate(@Param("TIME_STAMP")LocalDateTime time_stamp,
                                          @Param("PAIR_ID") long pair_id);


    CurrencyPairDataPoint save(CurrencyPairDataPoint currencyPairDataPoint);
}