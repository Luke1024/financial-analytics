package com.finance.mapper;

import com.finance.domain.Order;
import com.finance.domain.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    /*public List<OrderDto> mapToOrderDtoList(List<Order> orders){
        return orders.stream().map(order -> new OrderDto(order.getOrderId(),
                order.getLongShort(),
                order.getLot(),
                order.getCurrencyPair(),
                order.getStopLoss(),
                order.getTakeProfit(),
                order.getCurrencyPairHistoryPointOpen().getPointId(),
                order.getOrderOpened(),
                order.getCurrencyPairHistoryPointClose().getPointId(),
                order.getOrderClosed(),
                order.getOrderBalance(),
                order.getPointId()))
                .collect(Collectors.toList());
    }
    */
    /*
    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(order.getOrderId(),
                order.getLongShort(),
                order.getLot(),
                order.getCurrencyPair(),
                order.getStopLoss(),
                order.getTakeProfit(),
                order.getCurrencyPairHistoryPointOpen().getPointId(),
                order.getOrderOpened(),
                order.getOrderOpeningPrice(),
                order.getCurrencyPairHistoryPointClose().getPointId(),
                order.getOrderClosed(),
                order.getOrderClosingPrice(),
                order.getOrderBalance(),
                order.getTradingAccountHistoryPoint().getPointId());
    }
    */

}
