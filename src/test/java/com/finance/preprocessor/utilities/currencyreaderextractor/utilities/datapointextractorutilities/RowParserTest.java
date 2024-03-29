package com.finance.preprocessor.utilities.currencyreaderextractor.utilities.datapointextractorutilities;

import com.finance.preprocessor.utilities.DataPoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RowParserTest {

    @Autowired
    private RowParser rowParser;

    @Test
    public void testParsingCorrectRow(){
        String inputString = "2000.05.30,17:27,0.930200,0.930200,0.930200,0.930200,0";

        List<String> splittedRow = Arrays.asList(inputString.split(","));

        DataPoint dataPoint = rowParser.parse(splittedRow);

        DataPoint dataPointExpected = new DataPoint(
                LocalDateTime.of(2000,5,30,17,27),0.930200);

        Assert.assertEquals(dataPointExpected.toString(), dataPoint.toString());
    }

    @Test
    public void testParsingIncorrectRow(){
        String inputString = "2000.05.30,17:27,0.93z0200,0.930200,0.930200,0.930200,0";

        List<String> splittedRow = Arrays.asList(inputString.split(","));

        DataPoint dataPoint = rowParser.parse(splittedRow);

        Assert.assertEquals(null, dataPoint);
    }
}