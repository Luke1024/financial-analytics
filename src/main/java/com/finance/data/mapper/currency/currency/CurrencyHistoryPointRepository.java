package com.finance.data.mapper.currency.currency;

import com.finance.data.domain.currency.CurrencyHistoryPoint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CurrencyHistoryPointRepository extends CrudRepository<CurrencyHistoryPoint, Long> {
    @Query
    List<CurrencyHistoryPoint> retrieveByTimeRangeAndCurrencyId(@Param("TIME_STAMP_START")LocalDateTime time_stamp_start,
                                                                @Param("TIME_STAMP_STOP")LocalDateTime time_stamp_stop,
                                                                @Param("CURRENCY_ID")Long currency_id);
}
