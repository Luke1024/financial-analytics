package com.finance.preprocessor.utilities.dataPointStatusUtilities;

import java.util.Arrays;
import java.util.List;

public class RowAnalyzer {

    public Analysis analyze(List<String> row){
        boolean columnNumber = checkColumnNumber(row);
        boolean date = checkDateNumbers(row);
        boolean time = checkTime(row);
        boolean value = checkValue(row);

        return new Analysis(columnNumber, date, time, value);
    }

    private boolean checkColumnNumber(List<String> row) {
        if(row.size() == 7) return true;
        return false;
    }

    private boolean checkDateNumbers(List<String> row){
        try {
            String dateNumbers = row.get(0);
            List<String> numbers = Arrays.asList(dateNumbers.split("."));
            if(numbers.size() == 3) return true;
            else return false;
        } catch (Exception e){
            return false;
        }
    }

    private boolean checkTime(List<String> row){
        try{
            String timeNumbers = row.get(1);
            List<String> numbers = Arrays.asList(timeNumbers.split(":"));
            if(numbers.size() == 2) return true;
            else return false;
        } catch (Exception e){
            return false;
        }
    }

    private boolean checkValue(List<String> row){
        try{
            String value = row.get(2);
            List<String> numbers = Arrays.asList(value.split("."));
            if(numbers.size() == 2) return true;
            else return false;
        } catch (Exception e){
            return false;
        }
    }
}
