package com.finance.data.service.currency;

import com.finance.data.domain.accounts.UserTradingAccount;
import com.finance.data.domain.currency.Order;
import com.finance.data.domain.currency.dto.OrderOpeningDto;
import com.finance.data.mapper.currency.currency.OrderRepository;
import com.finance.data.repository.accounts.TradingAccountRepository;
import com.finance.data.service.currency.orderserviceutilities.OrderOpeningEvaluator;
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

    public List<Order> getUserOrdersFromLastMonth(Long userId) {
        return orderRepository.findOrdersNewerThan(LocalDateTime.now().minusMonths(1));
    }

    public boolean placeOrder(OrderOpeningDto orderOpeningDto){
        Optional<UserTradingAccount> retrievedTradingAccount =
                tradingAccountRepository.findById(orderOpeningDto.getUserTradingAccountId());
        if(retrievedTradingAccount.isPresent()) {
             orderOpeningEvaluator.evaluate(orderOpeningDto, retrievedTradingAccount.get());
        } else {
            //account is not available
        }


    }
}
