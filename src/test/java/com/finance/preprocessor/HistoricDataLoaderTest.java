package com.finance.preprocessor;

import org.junit.Test;

public class HistoricDataLoaderTest {

    private HistoricDataLoader preprocessor = new HistoricDataLoader();

    @Test
    public void testPreprocessor(){
        preprocessor.loadDataIntoDatabase();
    }

}