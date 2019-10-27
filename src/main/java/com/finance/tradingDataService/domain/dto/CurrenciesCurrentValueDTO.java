package com.finance.tradingDataService.domain.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class CurrenciesCurrentValueDTO {
    private LocalDateTime timeStamp;
    private Map<String,Double> values;
}
