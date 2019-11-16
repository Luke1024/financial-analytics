package com.finance.data.controller.currency;

import com.finance.data.domain.currency.dto.OrderDto;
import com.finance.data.domain.currency.dto.OrderOpeningClosingDto;
import com.finance.data.mapper.currency.OrderMapper;
import com.finance.data.service.currency.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/finance")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    public List<OrderDto> getUserOrdersFromLastMonth(Long userId){
        return orderMapper.mapToOrderDtoList(orderService.getUserOrdersFromLastMonth(userId));
    }

    public boolean placeOrder(OrderOpeningClosingDto orderOpeningClosingDto){
        return orderService.placeOrder(orderOpeningClosingDto);
    }

    public boolean closeOrder(OrderOpeningClosingDto orderOpeningClosingDto){
        return orderService.placeOrder(orderOpeningClosingDto);
    }
}
