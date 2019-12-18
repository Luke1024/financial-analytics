package com.finance.data.service.currency;

import com.finance.data.domain.accounts.enums.OperationType;
import com.finance.data.domain.accounts.TradingAccount;
import com.finance.data.domain.accounts.TradingAccountHistoryPoint;
import com.finance.data.domain.currency.CurrencyPair;
import com.finance.data.domain.currency.CurrencyPairHistoryPoint;
import com.finance.data.domain.currency.Order;
import com.finance.data.domain.currency.dto.OrderModDto;
import com.finance.data.domain.currency.dto.OrderOpeningDto;
import com.finance.data.repository.currency.OrderRepository;
import com.finance.data.repository.accounts.TradingAccountRepository;
import com.finance.data.service.account.UserService;
import com.finance.data.service.currency.orderserviceutilities.OrderOpeningEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
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
    private UserService userService;

    public List<Order> getCurrentlyOpenOrders(Long userId) {
        return orderRepository.findOrderByOrderClosedNull(userId);
    }

    public List<Order> getUserOrdersFromLastMonth(Long userId) {
        return userService.getUserById(userId)
                .getTradingAccounts()
                .stream()
                .map(tradingAccount -> tradingAccount.getPoints())
                .flatMap(Collection::parallelStream)
                .map(tradingAccountHistoryPoint -> tradingAccountHistoryPoint.getOrder())


        //return orderRepository.findOrderByC(userId ,LocalDateTime.now().minusMonths(1))
    }

    public boolean placeOrder(OrderOpeningDto orderOpeningDto) {
        Optional<TradingAccount> retrievedTradingAccount =
                tradingAccountRepository.findById(orderOpeningDto.getUserTradingAccountId());
        if(retrievedTradingAccount.isPresent()) {
            evaluateAndIfOkOpenOrder(orderOpeningDto, retrievedTradingAccount.get());
        } else {
            //account is not available
        }
    }

    private boolean evaluateAndIfOkOpenOrder(OrderOpeningDto orderOpeningDto, TradingAccount tradingAccount) {


        TradingAccountHistoryPoint newTradingAccountHistoryPoint =
                new TradingAccountHistoryPoint(
                        OperationType.TRADE,
                        null,
                        tradingAccount.getAmount(),
                        null,
                        null,
                        new Order);


        if(orderOpeningEvaluator.evaluateOrder(orderOpeningDto, tradingAccount)){
            Order newOrder = new Order(orderOpeningDto.getLongShort(),
                    orderOpeningDto.getLot(),
                    orderOpeningDto.getCurrencyPair(),
                    orderOpeningDto.getStopLoss(),
                    orderOpeningDto.getTakeProfit(),
                    getLastHistoryPoint(orderOpeningDto.getCurrencyPair()),
                    LocalDateTime.now(),
                    null,
                    null,
                    null,

                    );
            orderRepository.save(new Order());
            return true;
        } else {
            return false;
        }
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
    }

    public boolean closeOrder(Long orderId) {

    }
}
