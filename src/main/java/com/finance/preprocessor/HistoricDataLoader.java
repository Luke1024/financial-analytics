package com.finance.preprocessor;

import com.finance.preprocessor.utilities.*;
import com.finance.preprocessor.utilities.currencyreaderextractor.CurrencyReaderExtractor;
import com.finance.preprocessor.utilities.DataBaseLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HistoricDataLoader {

    @Autowired
    private CurrencyReaderExtractor currencyReaderExtractor;
    @Autowired
    private DataBaseLoader dataBaseLoader;

    public void loadDataIntoDatabase() {
        List<CurrencyFile> files = new ArrayList<>(Arrays.asList(
                //new CurrencyFile("EUR/GBP", "C:\\Users\\Luke\\IdeaProjects\\financial-analytics\\src\\main\\java\\com\\finance\\preprocessor\\data\\eurgbp"),
                new CurrencyFile("EUR/USD","C:\\Users\\Luke\\IdeaProjects\\financial-analytics\\src\\main\\java\\com\\finance\\preprocessor\\data\\eurusd")
        ));

        List<CurrencyPairDataPack> pairDataPacks = currencyReaderExtractor.readAndProcess(files, ChronoUnit.HOURS, ChronoUnit.MINUTES);
        dataBaseLoader.load(pairDataPacks);
    }
}
