package com.finance.preprocessor.utilities.currencyreaderextractor.utilities;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvReaderTest {

    @Autowired
    private CsvReader csvReader;

    @Test
    @Ignore
    public void testReadingCsv(){
        /*String pathToFile = "C:\\Users\\Luke\\IdeaProjects\\financial-analytics\\src\\test\\resources\\CsvReaderCsvFileForTest.csv";

        List<List<String>> numbersFromCsv = csvReader.read(pathToFile);

        Assert.assertEquals("2000.05.30",numbersFromCsv.get(0).get(0));
        Assert.assertEquals("17:27", numbersFromCsv.get(0).get(1));
        Assert.assertEquals("0.930200", numbersFromCsv.get(0).get(4));
        Assert.assertEquals("0.929700", numbersFromCsv.get(4).get(4));

         */
    }
}