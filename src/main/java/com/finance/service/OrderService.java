package com.finance.service;

import com.finance.domain.enums.OperationType;
import com.finance.domain.TradingAccount;
import com.finance.domain.TradingAccountHistoryPoint;
import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairHistoryPoint;
import com.finance.domain.Order;
import com.finance.domain.dto.OrderModDto;
import com.finance.domain.dto.OrderOpeningDto;
import com.finance.repository.OrderRepository;
import com.finance.repository.TradingAccountRepository;
import com.finance.service.orderserviceutilities.OrderEvaluatorResponseDto;
import com.finance.service.orderserviceutilities.OrderOpeningEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TradingAccountRepository tradingAccountRepository;

    @Autowired
    private CurrencyPairService currencyPairService;

    @Autowired
    private OrderOpeningEvaluator orderOpeningEvaluator;

    @Autowired
    private CalculatingService calculatingService;

    public List<Order> getCurrentlyOpenOrders(Long userId) {
        return orderRepository.findOrderByOrderClosedNull(userId);
    }

    public boolean placeOrder(OrderOpeningDto orderOpeningDto) {
        Optional<TradingAccount> retrievedTradingAccount =
                tradingAccountRepository.findById(orderOpeningDto.getUserTradingAccountId());
        if(retrievedTradingAccount.isPresent()) {
            return evaluateAndIfOkOpenOrder(orderOpeningDto, retrievedTradingAccount.get());
        } else {
            return false;
        }
    }

    private boolean evaluateAndIfOkOpenOrder(OrderOpeningDto orderOpeningDto, TradingAccount tradingAccount) {

        Order order = initializeOrder(orderOpeningDto);

        OrderEvaluatorResponseDto orderEvaluatorResponseDto = orderOpeningEvaluator.evaluate(orderOpeningDto, tradingAccount);

        if(orderEvaluatorResponseDto.isOpen()) {
            tradingAccount.getOpenOrders().add(order);
            tradingAccountRepository.save(tradingAccount);
            return true;
        } else {
            return false;
        }
    }

    private Order initializeOrder(OrderOpeningDto orderOpeningDto) {
        return new Order(orderOpeningDto.getLongShort(),
                orderOpeningDto.getLot(),
                orderOpeningDto.getCurrencyPair(),
                orderOpeningDto.getStopLoss(),
                orderOpeningDto.getTakeProfit(),
                getLastHistoryPoint(orderOpeningDto.getCurrencyPair()),
                LocalDateTime.now(),
                null, null, 0);
    }

    private CurrencyPairHistoryPoint getLastHistoryPoint(String currencyPairName) {
        Optional<CurrencyPair> retrievedCurrencyPair = currencyPairService.getCurrencyPair(currencyPairName);
        if(retrievedCurrencyPair.isPresent()){
            return retrievedCurrencyPair.get().getLastPairHistoryPoint();
        } else {
            //CurrencyPair not found
            return null;
        }
    }

    public Order modifyOrder(OrderModDto orderModDto) {
        Optional<Order> retrievedOrder = orderRepository.findById(orderModDto.getOrderId());
        if(retrievedOrder.isPresent()){
            return orderRepository.save(modifyOrder(orderModDto, retrievedOrder.get()));
        } else {
            return null;
        }
    }

    private Order modifyOrder(OrderModDto orderModDto, Order order) {
        order.setStopLoss(orderModDto.getStopLoss());
        order.setTakeProfit(orderModDto.getTakeProfit());
        return order;
    }

    public boolean closeOrder(Long orderId) {
        Optional<Order> retrievedOrder = orderRepository.findById(orderId);
        if(retrievedOrder.isPresent()) {
            orderRepository.save(setOrderToClose(retrievedOrder.get()));
            return true;
        } else {
            return false;
        }
    }

    private Order setOrderToClose(Order order) {
        order.setOrderClosed(LocalDateTime.now());
        order.setOrderBalance(calculateClosingBalance(order));
        return order;
    }

    private double calculateClosingBalance(Order order) {
        return calculatingService.calculateOrderPriceChange(order);
    }
}
