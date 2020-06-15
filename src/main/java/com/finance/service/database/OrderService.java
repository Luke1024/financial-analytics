package com.finance.service.database;

import com.finance.domain.TradingAccount;
import com.finance.domain.CurrencyPair;
import com.finance.domain.CurrencyPairDataPoint;
import com.finance.domain.Order;
import com.finance.domain.dto.OrderModDto;
import com.finance.domain.dto.OrderOpeningDto;
import com.finance.repository.OrderRepository;
import com.finance.service.CalculatingService;
import com.finance.service.database.orderserviceutilities.OrderEvaluatorResponseDto;
import com.finance.service.database.orderserviceutilities.OrderOpeningEvaluator;
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
    private TradingAccountService tradingAccountService;

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
        /*
        TradingAccount retrievedTradingAccount =
                tradingAccountService.getTradingAccountByAccountId(
                        orderOpeningDto.getTradingAccountId());
        if(retrievedTradingAccount != null) {
            return evaluateAndIfOkOpenOrder(orderOpeningDto, retrievedTradingAccount);
        } else {

         */
            return false;
        //}
    }

/*
    public Order modifyOpenOrder(OrderModDto orderModDto) {
        TradingAccount retrievedTradingAccount =
                tradingAccountService.getTradingAccountByAccountId(orderModDto.getTradingAccountId());
        if(retrievedTradingAccount != null){
            Order order = retrieveOrder(retrievedTradingAccount, orderModDto);
            order = modifyOpenOrder(orderModDto, order);
            return orderRepository.save(order);
        } else return null;
    }

 */

    public boolean closeOrder(Long orderId) {
        /*
        Optional<Order> retrievedOrder = orderRepository.findById(orderId);
        if(retrievedOrder.isPresent()) {
            orderRepository.save(setOrderToClose(retrievedOrder.get()));
            return true;
        } else {

         */
            return false;
        //}
    }

    private Order retrieveOrder(TradingAccount tradingAccount, OrderModDto orderModDto){
        return tradingAccount.getOpenOrders().stream()
                .filter(order -> order.getOrderId().equals(orderModDto.getOrderId()))
                .findAny().orElse(null);
    }

    /*
    private boolean evaluateAndIfOkOpenOrder(OrderOpeningDto orderOpeningDto, TradingAccount tradingAccount) {

        Order order = initializeOrder(orderOpeningDto);

        OrderEvaluatorResponseDto orderEvaluatorResponseDto =
                orderOpeningEvaluator.evaluate(orderOpeningDto, tradingAccount);

        if(orderEvaluatorResponseDto.isOpen()) {
            tradingAccount.getOpenOrders().add(order);
            order.setTradingAccount(tradingAccount);
            tradingAccountService.saveTradingAccount(tradingAccount);
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

    private CurrencyPairDataPoint getLastHistoryPoint(String currencyPairName) {
        DatabaseResponse databaseResponse = currencyPairService.getCurrencyPair(currencyPairName);

        //Optional<CurrencyPair> retrievedCurrencyPair =
        if(retrievedCurrencyPair.get() != null){
            return null; //retrievedCurrencyPair.get().getLastPairHistoryPoint();
        } else {
            //CurrencyPair not found
            return null;
        }
    }

    private Order setOrderToClose(Order order) {
        order.setOrderClosed(LocalDateTime.now());
        order.setOrderBalance(calculateClosingBalance(order));
        return order;
    }


    private Order modifyOpenOrder(OrderModDto orderModDto, Order order) {
        order.setStopLoss(orderModDto.getStopLoss());
        order.setTakeProfit(orderModDto.getTakeProfit());
        return order;
    }

    private double calculateClosingBalance(Order order) {
        return calculatingService.calculateOrderPriceChange(order);
    }

     */
}