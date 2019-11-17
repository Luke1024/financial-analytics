package com.finance.data.service.currency.orderserviceutilities;

import com.finance.data.domain.accounts.UserTradingAccount;
import com.finance.data.domain.currency.CurrencyHistoryPoint;
import com.finance.data.domain.currency.dto.OrderOpeningDto;
import com.finance.data.service.currency.CurrencyHistoryPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderOpeningEvaluator {
    @Autowired
    private CurrencyHistoryPointService currencyHistoryPointService;

    public OrderEvaluatorResponseDto evaluate(OrderOpeningDto orderOpeningDto, UserTradingAccount userTradingAccount) {
        boolean accountBalance = evaluateAccountBalance(orderOpeningDto, userTradingAccount);
        boolean takeProfitValueCorrect = evaluateTakeProfit(orderOpeningDto);
        boolean stopLossValueCorrect = evaluateStopLoss(orderOpeningDto);
    }

    private boolean evaluateAccountBalance(OrderOpeningDto orderOpeningDto, UserTradingAccount userTradingAccount) {
        CurrencyHistoryPoint baseCurrency = currencyHistoryPointService
                .getLastCurrencyHistoryPoint(orderOpeningDto.getBaseCurrency());
        CurrencyHistoryPoint currency = currencyHistoryPointService
                .getLastCurrencyHistoryPoint(orderOpeningDto.getCurrency());

        double baseCurrencyValue = baseCurrency.
    }
}
