package com.finance.data.mapper.currency;

import com.finance.data.domain.currency.Order;
import com.finance.data.domain.currency.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public List<OrderDto> mapToOrderDtoList(List<Order> orders){
        return orders.stream().map(order -> new OrderDto()).collect(Collectors.toList());
    }
}
