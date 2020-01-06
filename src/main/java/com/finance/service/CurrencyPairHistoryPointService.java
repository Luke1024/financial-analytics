package com.finance.service;

import com.finance.domain.CurrencyPairHistoryPoint;
import com.finance.domain.dto.PairHistoryRequestDto;
import com.finance.repository.CurrencyPairHistoryPointRepository;
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
