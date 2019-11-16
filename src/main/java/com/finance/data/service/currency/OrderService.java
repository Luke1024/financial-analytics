package com.finance.data.service.currency;

import com.finance.data.domain.accounts.UserTradingAccount;
import com.finance.data.domain.currency.Order;
import com.finance.data.domain.currency.dto.OrderOpeningClosingDto;
import com.finance.data.mapper.currency.currency.OrderRepository;
import com.finance.data.repository.accounts.TradingAccountRepository;
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

    public List<Order> getUserOrdersFromLastMonth(Long userId){
        return orderRepository.findOrdersNewerThan(LocalDateTime.now().minusMonths(1));
    }

    public boolean placeOrder(OrderOpeningClosingDto orderOpeningClosingDto){
        Optional<UserTradingAccount> retrievedTradingAccount =
                tradingAccountRepository.findById(orderOpeningClosingDto.getUserTradingAccountId());
        if(retrievedTradingAccount.isPresent()) {
            orderRepository.save(new Order(orderOpeningClosingDto.getLongShort(),
                    orderOpeningClosingDto.getLot(),
                    orderOpeningClosingDto.getCurrencyPair(),
                    orderOpeningClosingDto.getStopLoss(),
                    orderOpeningClosingDto.getTakeProfit(),
                    currencyPairService.getLastHistoryPoint(orderOpeningClosingDto.getCurrencyPair()),
                    LocalDateTime.now(),
                    null,
                    null,
                    null,
                    retrievedTradingAccount.get()));
            return true;
        } else { //account not found
            return false;
        }
    }
}
