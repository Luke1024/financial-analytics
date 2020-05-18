package com.finance.preprocessor.utilities;

import java.time.LocalDateTime;

public class DataPoint {
    private LocalDateTime localDateTime;
    private double value;

    public DataPoint(LocalDateTime localDateTime, double value) {
        this.localDateTime = localDateTime;
        this.value = value;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DataPoint{" +
                "localDateTime=" + localDateTime +
                ", value=" + value +
                '}';
    }
}
