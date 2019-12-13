package com.finance.data.domain.currency.dto;

public class OrderModDto {
    private Long orderId;
    private double stopLoss;
    private double takeProfit;

    public OrderModDto(Long orderId, double stopLoss, double takeProfit) {
        this.orderId = orderId;
        this.stopLoss = stopLoss;
        this.takeProfit = takeProfit;
    }

    public Long getOrderId() {
        return orderId;
    }

    public double getStopLoss() {
        return stopLoss;
    }

    public double getTakeProfit() {
        return takeProfit;
    }
}
