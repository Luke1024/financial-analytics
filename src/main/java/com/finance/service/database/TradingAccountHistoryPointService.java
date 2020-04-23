package com.finance.service.database;

import com.finance.domain.TradingAccountHistoryPoint;
import com.finance.repository.TradingAccountHistoryPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradingAccountHistoryPointService {
    @Autowired
    private TradingAccountHistoryPointRepository tradingAccountHistoryPointRepository;

    public List<TradingAccountHistoryPoint> getTradingAccountHistoryPoints(Long userId){
        return tradingAccountHistoryPointRepository.findByTradingAccountId(userId);
    }

}
