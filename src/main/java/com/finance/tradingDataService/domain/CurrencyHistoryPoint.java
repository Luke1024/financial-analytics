package com.finance.tradingDataService.domain;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class CurrencyHistoryPoint {

    private LocalDateTime timeStamp;
    private Double value;
}
