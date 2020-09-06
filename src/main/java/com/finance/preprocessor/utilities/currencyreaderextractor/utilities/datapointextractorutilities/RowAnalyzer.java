package com.finance.preprocessor.utilities.currencyreaderextractor.utilities.datapointextractorutilities;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class RowAnalyzer {

    private Logger logger = Logger.getLogger(RowAnalyzer.class.getName());

    public Analysis analyze(List<String> row){
        boolean columnNumber = checkColumnNumber(row);
        boolean date = checkDateNumbers(row);
        boolean time = checkTime(row);
        boolean value = checkValue(row);

        Analysis analysis = new Analysis(columnNumber, date, time, value);

        if( ! analysis.isRowCorrect()){
            logger.log(Level.INFO, "Row skipped due to inconsistencies in data.");
        }

        return new Analysis(columnNumber, date, time, value);
    }

    //checkColumnNumber
    private boolean checkColumnNumber(List<String> row) {
        return row.size() == 7;
    }

    //checkDateNumbers
    private boolean checkDateNumbers(List<String> row){
        try {
            String dateNumbers = row.get(0);
            String[] numbers = dateNumbers.split("\\.");
            if(numbers.length == 3) {
                boolean isYear = checkYear(numbers[0]);
                boolean isMonth = checkMonth(numbers[1]);
                boolean isDay = checkDay(numbers[2]);
                if (isYear && isMonth && isDay) return true;
            }
        } catch (Exception e){}
        return false;
    }

    private boolean checkYear(String year){
        try{
            if(year.length() == 4) {
                Integer.parseInt(year);
                return true;
            }
        } catch (Exception e){}
        return false;
    }

    private boolean checkMonth(String month){
        try{
            if(month.length()==2) {
                Integer.parseInt(month);
                return true;
            }
        } catch (Exception e){}
        return false;
    }

    private boolean checkDay(String day){
        try{
            if(day.length()==2) {
                Integer.parseInt(day);
                return true;
            }
        } catch (Exception e){}
        return false;
    }


    //checkTime
    private boolean checkTime(List<String> row){
        try{
            String timeNumbers = row.get(1);
            List<String> numbers = Arrays.asList(timeNumbers.split(":"));
            if(numbers.size() == 2) {
                if (checkHourAndMinuteNumbers(numbers)) {
                    return true;
                }
            }
        } catch (Exception e){}
        return false;
    }

    private boolean checkHourAndMinuteNumbers(List<String> numbers){
        try{
            String timeNumbers = numbers.get(0);
            if(timeNumbers.length()==2){
                Integer.parseInt(timeNumbers);
                return true;
            }
        } catch (Exception e){}
        return false;
    }

    //check value
    private boolean checkValue(List<String> row){
        try{
            String value = row.get(2);
            List<String> numbers = Arrays.asList(value.split("\\."));
            if(numbers.size() == 2){
                Integer.parseInt(numbers.get(0));
                Integer.parseInt(numbers.get(1));
                return true;
            }
        } catch (Exception e){}
        return false;
    }
}
