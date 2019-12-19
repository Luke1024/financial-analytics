package com.finance.data.service.currency.orderserviceutilities;

import com.finance.data.domain.accounts.TradingAccount;
import com.finance.data.domain.currency.CurrencyPairHistoryPoint;
import com.finance.data.domain.currency.dto.OrderOpeningDto;
import com.finance.data.service.currency.CurrencyPairHistoryPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderOpeningEvaluator {

    @Autowired
    private CurrencyPairHistoryPointService currencyPairHistoryPointService;


    public OrderEvaluatorResponseDto evaluate(OrderOpeningDto orderOpeningDto, TradingAccount userTradingAccount) {
        boolean accountBalance = evaluateAccountBalance(orderOpeningDto, userTradingAccount);
        boolean takeProfitValueCorrect = evaluateTakeProfit(orderOpeningDto);
        boolean stopLossValueCorrect = evaluateStopLoss(orderOpeningDto);
    }

    private boolean evaluateAccountBalance(OrderOpeningDto orderOpeningDto, TradingAccount userTradingAccount) {
        CurrencyPairHistoryPoint lastPairPoint =
                currencyPairHistoryPointService.getLastPointByCurrencyPair(orderOpeningDto.getBaseCurrency);

    }
}
