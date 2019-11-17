package com.finance.data.service.currency;

import com.finance.data.domain.currency.Currency;
import com.finance.data.domain.currency.CurrencyHistoryPoint;
import com.finance.data.mapper.currency.currency.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyHistoryPointService {
    @Autowired
    private CurrencyRepository currencyRepository;

    public CurrencyHistoryPoint getLastCurrencyHistoryPoint(String currencyKey){
        List<Currency> currencies = currencyRepository.findByCurrencyName(currencyKey);
        if(currencies.size()==1) {
            return currencies.get(0).getCurrencyHistoryPoints().get(currencies.get(0).getCurrencyHistoryPoints().size()-1);
        } else {
            return new CurrencyHistoryPoint();
        } //currency not found
    }
}
