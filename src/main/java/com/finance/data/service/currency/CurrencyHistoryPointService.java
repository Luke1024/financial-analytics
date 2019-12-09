package com.finance.data.service.currency;

import com.finance.data.domain.currency.CurrencyPair;
import com.finance.data.domain.currency.CurrencyPairHistoryPoint;
import com.finance.data.repository.currency.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyHistoryPointService {
    @Autowired
    private CurrencyRepository currencyRepository;

    public CurrencyPairHistoryPoint getLastCurrencyHistoryPoint(String currencyKey){
        List<CurrencyPair> currencies = currencyRepository.findByCurrencyName(currencyKey);
        if(currencies.size()==1) {
            return currencies.get(0).getCurrencyPairHistoryPoints().get(currencies.get(0).getCurrencyPairHistoryPoints().size()-1);
        } else {
            return new CurrencyPairHistoryPoint();
        } //currency not found
    }
}
