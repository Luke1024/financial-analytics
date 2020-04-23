package com.finance.service.database.orderserviceutilities;

import com.finance.domain.TradingAccount;
import com.finance.domain.dto.OrderOpeningDto;
import com.finance.domain.enums.LongShort;
import com.finance.service.database.CurrencyPairHistoryPointService;
import com.finance.service.database.OrderService;
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
