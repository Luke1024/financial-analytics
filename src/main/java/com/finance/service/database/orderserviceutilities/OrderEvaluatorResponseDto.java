package com.finance.service.database.orderserviceutilities;

public class OrderEvaluatorResponseDto {
    private boolean open;
    private String message;

    public OrderEvaluatorResponseDto(boolean open, String message) {
        this.open = open;
        this.message = message;
    }

    public boolean isOpen() {
        return open;
    }

    public String getMessage() {
        return message;
    }
}
