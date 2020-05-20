package com.finance.preprocessor.utilities.currencyReaderExtractor.utilities.dataPointExtractorUtilities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RowAnalyzerTest {

    @Autowired
    private RowAnalyzer rowAnalyzer;

    @Test
    public void testFullyCorrectRowAnalyzing(){

        List<String> row = new ArrayList<>();

        row.add("2000.05.30");
        row.add("17:27");
        row.add("0.930200");
        row.add("0.930200");
        row.add("0.930200");
        row.add("0.930200");
        row.add("0");

        Analysis analysis = rowAnalyzer.analyze(row);

        Assert.assertEquals(true, analysis.isRowCorrect());
    }

    @Test
    public void testRowWithWrongColumnNumber(){
        List<String> row = new ArrayList<>();

        row.add("2000.05.30");
        row.add("03:27");
        row.add("0.930200");
        row.add("0.930200");
        row.add("0.930200");
        row.add("0.930200");

        Analysis analysis = rowAnalyzer.analyze(row);

        Assert.assertEquals(false, analysis.isRowCorrect());
        Assert.assertEquals(false, analysis.isColumnNumber());
        Assert.assertEquals(true, analysis.isDate());
        Assert.assertEquals(true, analysis.isTime());
        Assert.assertEquals(true, analysis.isValue());
    }

    @Test
    public void testRowWithWrongDayTime(){
        List<String> row = new ArrayList<>();

        row.add("2000.05.30");
        row.add("z7:27");
        row.add("0.930200");
        row.add("0.930200");
        row.add("0.930200");
        row.add("0.930200");
        row.add("0");

        Analysis analysis = rowAnalyzer.analyze(row);

        Assert.assertEquals(false, analysis.isRowCorrect());
        Assert.assertEquals(true, analysis.isColumnNumber());
        Assert.assertEquals(true, analysis.isDate());
        Assert.assertEquals(false, analysis.isTime());
        Assert.assertEquals(true, analysis.isValue());
    }

    @Test
    public void testRowWithWrongDayTime2(){
        List<String> row = new ArrayList<>();

        row.add("2000.05.30");
        row.add("7:27");
        row.add("0.930200");
        row.add("0.930200");
        row.add("0.930200");
        row.add("0.930200");
        row.add("0");

        Analysis analysis = rowAnalyzer.analyze(row);

        Assert.assertEquals(false, analysis.isRowCorrect());
        Assert.assertEquals(true, analysis.isColumnNumber());
        Assert.assertEquals(true, analysis.isDate());
        Assert.assertEquals(false, analysis.isTime());
        Assert.assertEquals(true, analysis.isValue());
    }

    @Test
    public void testRowWithWrongDate(){
        List<String> row = new ArrayList<>();

        row.add("200.05.30");
        row.add("17:27");
        row.add("0.930200");
        row.add("0.930200");
        row.add("0.930200");
        row.add("0.930200");
        row.add("0");

        Analysis analysis = rowAnalyzer.analyze(row);

        Assert.assertEquals(false, analysis.isRowCorrect());
        Assert.assertEquals(true, analysis.isColumnNumber());
        Assert.assertEquals(false, analysis.isDate());
        Assert.assertEquals(true, analysis.isTime());
        Assert.assertEquals(true, analysis.isValue());
    }
}