package com.finance.tradingDataService.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class CSVReaderTest {
    @Autowired
    private CSVReader csvReader;

    @Test
    public void testReader() throws Exception {
        csvReader.readCsv("/home/luke/financialAnalytics/src/main/resources/symbols/stocklist.csv");
    }
}