package com.finance.preprocessor.utilities.currencyreaderextractor.utilities.datapointextractorutilities;

import com.finance.preprocessor.utilities.DataPoint;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RowParser {

    private Logger logger = Logger.getLogger(RowParser.class.getName());

    public DataPoint parse(List<String> row){
        LocalDate date = parseDate(row);
        LocalTime time = parseTime(row);
        double value = parseValue(row);

        if(date == null || time == null || value == -1.0){
            return null;
        }

        try {
            LocalDateTime timeStamp = LocalDateTime.of(
                    date.getYear(), date.getMonth(), date.getDayOfMonth(),
                    time.getHour(), time.getMinute());
            return new DataPoint(timeStamp, value);
        } catch (Exception e){
            logger.log(Level.WARNING, e.toString());
        }
        return null;
    }

    private LocalDate parseDate(List<String> row){
        try {
            List<String> dateNumbers = Arrays.asList(row.get(0).split("\\."));
            return LocalDate.of(
                    Integer.parseInt(dateNumbers.get(0)),
                    Integer.parseInt(dateNumbers.get(1)),
                    Integer.parseInt(dateNumbers.get(2)));
        } catch (Exception e) {
            logger.log(Level.WARNING, e.toString());
            return null;
        }
    }

    private LocalTime parseTime(List<String> row){
        try{
            List<String> timeNumbers = Arrays.asList(row.get(1).split(":"));
            return LocalTime.of(
                    Integer.parseInt(timeNumbers.get(0)),
                    Integer.parseInt(timeNumbers.get(1)));
        } catch (Exception e) {
            logger.log(Level.WARNING, e.toString());
            return null;
        }
    }

    private double parseValue(List<String> row){
        try {
            return Double.parseDouble(row.get(2));
        } catch (Exception e){
            logger.log(Level.WARNING, e.toString());
            return -1.0;
        }
    }
}
