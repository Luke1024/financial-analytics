package com.finance.preprocessor.utilities.currencyReaderExtractor;

import com.finance.preprocessor.CurrencyFile;
import com.finance.preprocessor.utilities.CurrencyPairDataPack;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyReaderExtractorTest {

    @Autowired
    private CurrencyReaderExtractor currencyReaderExtractor;

    @Test
    public void testCurrencyExtracting(){
        CurrencyFile currencyFile = new CurrencyFile("testPair",
                "C:\\Users\\Luke\\IdeaProjects\\financial-analytics\\src\\test\\java\\com\\finance\\preprocessor\\eurusd");

        List<CurrencyPairDataPack> currencyPairDataPack = currencyReaderExtractor.readAndProcess(Arrays.asList(currencyFile), ChronoUnit.HOURS, ChronoUnit.MINUTES);

        Assert.assertEquals(1, currencyPairDataPack.size());
        Assert.assertEquals(100,currencyPairDataPack.get(0).getDataPointList().size());
    }
}