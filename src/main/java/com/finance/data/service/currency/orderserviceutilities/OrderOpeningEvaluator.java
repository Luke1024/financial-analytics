package com.finance.data.service.currency.orderserviceutilities;

import com.finance.data.domain.accounts.TradingAccount;
import com.finance.data.domain.currency.CurrencyPairHistoryPoint;
import com.finance.data.domain.currency.dto.OrderOpeningDto;
import com.finance.data.domain.currency.enums.LongShort;
import com.finance.data.service.currency.CalculatingService;
import com.finance.data.service.currency.CurrencyPairHistoryPointService;
import com.finance.data.service.currency.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderOpeningEvaluator {

    @Autowired
    private CurrencyPairHistoryPointService currencyPairHistoryPointService;

    @Autowired
    private OrderService orderService;

    public OrderEvaluatorResponseDto evaluate(OrderOpeningDto orderOpeningDto, TradingAccount userTradingAccount) {
        String message = "";
        message += evaluateAccountBalance(orderOpeningDto, userTradingAccount);
        message += evaluateTakeProfitStopLoss(orderOpeningDto);
        if(message.length()>0){
            return new OrderEvaluatorResponseDto(false, message);
        } else {
            return new OrderEvaluatorResponseDto(true, message);
        }

    }

    private String evaluateAccountBalance(OrderOpeningDto orderOpeningDto, TradingAccount userTradingAccount) {
        int leverage = userTradingAccount.getLeverage();
        double amount = userTradingAccount.getAmount();
        double lot = orderOpeningDto.getLot();

        if(lot * 100000 > amount * leverage) {
            return  "";
        } else {
            return "Not enough account balance";
        }
    }

    private String evaluateTakeProfitStopLoss(OrderOpeningDto orderOpeningDto) {
        String message = "";

        if(orderOpeningDto.getLongShort() == LongShort.LONG){
            message += evaluateLong(orderOpeningDto);
        }
        if(orderOpeningDto.getLongShort() == LongShort.SHORT){
            message += evaluateShort(orderOpeningDto);
        }
        if(orderOpeningDto.getLongShort() == null){
            message += "LongShort is null";
        }
        return message;
    }

    private String evaluateLong(OrderOpeningDto orderOpeningDto){
        String message = "";
        double takeProfit = orderOpeningDto.getTakeProfit();
        double stopLoss = orderOpeningDto.getStopLoss();

        if(takeProfit <= stopLoss) {
            message = "SL cannot be higher than TP";
        }
        return message;
    }

    private String evaluateShort(OrderOpeningDto orderOpeningDto){
        String message = "";
        double takeProfit = orderOpeningDto.getTakeProfit();
        double stopLoss = orderOpeningDto.getStopLoss();

        if(takeProfit >= stopLoss) {
            message = "SL cannot be lower than TP";
        }
        return message;
    }
}
