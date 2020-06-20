package com.finance.preprocessor.utilities.currencyreaderextractor;

import com.finance.preprocessor.CurrencyFile;
import com.finance.preprocessor.utilities.CurrencyPairDataPack;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.time.temporal.ChronoUnit.HOURS;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyReaderExtractorTest {

    @Autowired
    private CurrencyReaderExtractor currencyReaderExtractor;

    @Test
    @Ignore
    public void testCurrencyExtracting(){
        //CurrencyFile currencyFile = new CurrencyFile("testPair",
          //      "C:\\Users\\Luke\\IdeaProjects\\financial-analytics\\src\\test\\java\\com\\finance\\preprocessor\\eurusd");

        //List<CurrencyPairDataPack> currencyPairDataPack = currencyReaderExtractor.readAndProcess(currencyFile, ChronoUnit.HOURS, ChronoUnit.MINUTES);

        //Assert.assertEquals(1, currencyPairDataPack.size());
        //Assert.assertEquals(2085,currencyPairDataPack.get(0).getDataPointList().size());
    }
}