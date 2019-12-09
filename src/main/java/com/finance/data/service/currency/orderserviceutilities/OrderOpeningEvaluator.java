package com.finance.data.service.currency.orderserviceutilities;

import com.finance.data.service.currency.CurrencyHistoryPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderOpeningEvaluator {
    @Autowired
    private CurrencyHistoryPointService currencyHistoryPointService;

    //public OrderEvaluatorResponseDto evaluate(OrderOpeningDto orderOpeningDto, TradingAccount userTradingAccount) {
        //boolean accountBalance = evaluateAccountBalance(orderOpeningDto, userTradingAccount);
        //boolean takeProfitValueCorrect = evaluateTakeProfit(orderOpeningDto);
        //boolean stopLossValueCorrect = evaluateStopLoss(orderOpeningDto);
    //}

    //private boolean evaluateAccountBalance(OrderOpeningDto orderOpeningDto, TradingAccount userTradingAccount) {
      //  CurrencyPairHistoryPoint baseCurrency = currencyHistoryPointService
        //        .getLastCurrencyHistoryPoint(orderOpeningDto.getBaseCurrency());
        //CurrencyPairHistoryPoint currency = currencyHistoryPointService
      //          .getLastCurrencyHistoryPoint(orderOpeningDto.getCurrency());

        //double baseCurrencyValue = b
    //}
}
