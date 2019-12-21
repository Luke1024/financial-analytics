package com.finance.repository;

import com.finance.domain.CurrencyPairHistoryPoint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CurrencyPairHistoryPointRepository extends CrudRepository<CurrencyPairHistoryPoint, Long> {
    @Query
    List<CurrencyPairHistoryPoint> retrieveByTimeRangeAndCurrencyName(@Param("TIME_STAMP_START")LocalDateTime time_stamp_start,
                                                                      @Param("TIME_STAMP_STOP")LocalDateTime time_stamp_stop,
                                                                      @Param("CURRENCY_NAME")String currency_name);
}
