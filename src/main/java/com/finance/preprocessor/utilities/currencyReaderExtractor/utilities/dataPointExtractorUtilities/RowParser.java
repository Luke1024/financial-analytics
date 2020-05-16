package com.finance.preprocessor.utilities.currencyReaderExtractor.utilities.dataPointExtractorUtilities;

import com.finance.preprocessor.utilities.DataPoint;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class RowParser {
    public DataPoint parse(List<String> row){
        LocalDate date = parseDate(row);
        LocalTime time = parseTime(row);
        double value = parseValue(row);
        LocalDateTime timeStamp = LocalDateTime.of(
                date.getYear(), date.getMonth(), date.getDayOfMonth(),
                time.getHour(), time.getMinute());

        return new DataPoint(timeStamp, value);
    }

    private LocalDate parseDate(List<String> row){
        try {
            List<String> dateNumbers = Arrays.asList(row.get(0).split("."));
            return LocalDate.of(
                    Integer.parseInt(dateNumbers.get(0)),
                    Integer.parseInt(dateNumbers.get(1)),
                    Integer.parseInt(dateNumbers.get(2)));
        } catch (Exception e) {
            return LocalDate.of(1950,1,1);
        }
    }

    private LocalTime parseTime(List<String> row){
        try{
            List<String> timeNumbers = Arrays.asList(row.get(1).split(":"));
            return LocalTime.of(
                    Integer.parseInt(timeNumbers.get(0)),
                    Integer.parseInt(timeNumbers.get(1)));
        } catch (Exception e) {
            return LocalTime.of(0,0);
        }
    }

    private double parseValue(List<String> row){
        try {
            return Double.parseDouble(row.get(2));
        } catch (Exception e){
            return 0.0;
        }
    }
}
