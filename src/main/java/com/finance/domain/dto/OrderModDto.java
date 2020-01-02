package com.finance.domain.dto;

public class OrderModDto {
    private Long userId;
    private Long tradingAccountId;
    private Long orderId;
    private double stopLoss;
    private double takeProfit;

    public OrderModDto(Long userId, Long tradingAccountId, Long orderId, double stopLoss, double takeProfit) {
        this.userId = userId;
        this.tradingAccountId = tradingAccountId;
        this.orderId = orderId;
        this.stopLoss = stopLoss;
        this.takeProfit = takeProfit;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTradingAccountId() {
        return tradingAccountId;
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
