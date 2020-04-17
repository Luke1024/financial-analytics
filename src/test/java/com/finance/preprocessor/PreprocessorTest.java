package com.finance.preprocessor;

import org.junit.Test;

public class PreprocessorTest {

    private Preprocessor preprocessor = new Preprocessor();

    @Test
    public void testPreprocessor(){
        preprocessor.loadDataIntoDatabase();
    }

}