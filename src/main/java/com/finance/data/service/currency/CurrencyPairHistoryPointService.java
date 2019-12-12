package com.finance.data.service.currency;

import com.finance.data.domain.currency.CurrencyPairHistoryPoint;
import com.finance.data.domain.currency.dto.PairHistoryRequestDto;
import com.finance.data.repository.currency.CurrencyPairHistoryPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyPairHistoryPointService {

    @Autowired
    private CurrencyPairHistoryPointRepository repository;

    public List<CurrencyPairHistoryPoint> getCurrencyPairHistory(PairHistoryRequestDto pairHistoryRequestDto){
        return repository.retrieveByTimeRangeAndCurrencyName(
                pairHistoryRequestDto.getStart(), pairHistoryRequestDto.getStop(), pairHistoryRequestDto.getPairName());
    }
}
