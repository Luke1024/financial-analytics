package com.finance.preprocessor;

import com.finance.preprocessor.utilities.CsvReader;

import java.util.List;

public class Preprocessor {
    private CsvReader csvReader = new CsvReader();

    public void loadDataIntoDatabase() {
        List<List<String>> output = csvReader.read("DAT_MT_EURUSD_M1_2000.csv");

        for(List list : output){
            for(Object st : list){
                System.out.println(st);
            }
        }
    }
}
