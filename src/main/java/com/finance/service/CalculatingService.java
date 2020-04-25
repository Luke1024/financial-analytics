package com.finance.service;

import com.finance.domain.Order;
import com.finance.domain.enums.LongShort;
import com.finance.service.database.CurrencyPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatingService {

    @Autowired
    private CurrencyPairService currencyPairService;

    public double calculateOrderPriceChange(Order order) {

        double lot = 0;
        double valueOpen = 0;
        double valueCurrent = 0;
        LongShort longShort = null;
        double computedValue = 0;

        try {
            lot = order.getLot();
            valueOpen = order.getCurrencyPairDataPointOpen().getValue();
            valueCurrent = getLastValue(order.getCurrencyPair());
            longShort = order.getLongShort();
        } catch (Exception e) { }

        if(longShort==LongShort.LONG) {
            computedValue = lot * 100000 * (valueCurrent - valueOpen);
        }
        if(longShort==LongShort.SHORT){
            computedValue = lot * 100000 * (valueCurrent -(-valueOpen));
        }
        return computedValue;
    }

    private double getLastValue(String currencyPair) {
        double value=0;
        try{
            value = 0; //currencyPairService.getCurrencyPair(currencyPair).get().getLastPairHistoryPoint().getValue();
        } catch (Exception e) {}
        return value;
    }
}