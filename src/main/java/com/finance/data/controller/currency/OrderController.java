package com.finance.data.controller.currency;

import com.finance.data.domain.currency.dto.OrderDto;
import com.finance.data.domain.currency.dto.OrderModDto;
import com.finance.data.domain.currency.dto.OrderOpeningDto;
import com.finance.data.mapper.currency.OrderMapper;
import com.finance.data.service.currency.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/finance")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping(value = "/orders")
    public List<OrderDto> getUserOpenOrders(@PathVariable Long userId) {
        return orderMapper.mapToOrderDtoList(orderService.getCurrentlyOpenOrders(userId));
    }

    @GetMapping(value = "/orders/last")
    public List<OrderDto> getUserOrdersFromLastMonth(@PathVariable Long userId){
        return orderMapper.mapToOrderDtoList(orderService.getUserOrdersFromLastMonth(userId));
    }

    @GetMapping(value = "/orders/open")
    public List<OrderDto> getOpenOrders(@PathVariable Long userId) {
        return orderMapper.mapToOrderDtoList(orderService.getCurrentlyOpenOrders(userId));
    }

    @PostMapping(value = "/order/new", consumes = APPLICATION_JSON_VALUE)
    public boolean placeOrder(@RequestBody OrderOpeningDto orderOpeningDto){
        return orderService.placeOrder(orderOpeningDto);
    }

    @PutMapping(value = "/order/mod", consumes = APPLICATION_JSON_VALUE)
    public OrderDto modifyOrder(OrderModDto orderModDto) {
        return orderMapper.mapToOrderDto(orderService.modifyOrder(orderModDto));
    }

    @DeleteMapping(value = "/order/delete", consumes = APPLICATION_JSON_VALUE)
    public boolean closeOrder(Long orderId) {
        return orderService.closeOrder(orderId);
    }
}
