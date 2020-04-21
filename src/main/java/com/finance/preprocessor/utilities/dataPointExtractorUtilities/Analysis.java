package com.finance.preprocessor.utilities.dataPointExtractorUtilities;

import com.finance.preprocessor.utilities.DataPointStatus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Analysis {
    boolean columnNumber;
    boolean date;
    boolean time;
    boolean value;

    public Analysis(boolean columnNumber, boolean date, boolean time, boolean value) {
        this.columnNumber = columnNumber;
        this.date = date;
        this.time = time;
        this.value = value;
    }

    public boolean isColumnNumber() {
        return columnNumber;
    }

    public boolean isDate() {
        return date;
    }

    public boolean isTime() {
        return time;
    }

    public boolean isValue() {
        return value;
    }

    public boolean isRowCorrect(){
        return columnNumber && date && time && value;
    }

    public DataPointStatus getRaport() {
        List<Field> analysisField = Arrays.asList(Analysis.class.getDeclaredFields());
        List<String> elementNames = new ArrayList<>();
        List<Boolean> elementStatuses = new ArrayList<>();

        try{
            for(Field field : analysisField) {
                elementNames.add(field.getName());
                elementStatuses.add((Boolean) field.get(field.getName()));
            }
        } catch (Exception e){}
        return new DataPointStatus(elementNames, elementStatuses);
    }
}
