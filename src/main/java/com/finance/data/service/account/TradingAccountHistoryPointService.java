package com.finance.data.service.account;

import com.finance.data.domain.accounts.TradingAccountHistoryPoint;
import com.finance.data.repository.accounts.TradingAccountHistoryPointRepository;
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
