package com.finance.preprocessor;

import com.finance.preprocessor.utilities.*;
import com.finance.preprocessor.utilities.currencyReaderExtractor.CurrencyReaderExtractor;
import com.finance.preprocessor.utilities.DataBaseLoader;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HistoricDataLoader {

    private CurrencyReaderExtractor currencyReaderExtractor = new CurrencyReaderExtractor();
    private DataBaseLoader dataBaseLoader = new DataBaseLoader();

    public void loadDataIntoDatabase() {
        List<CurrencyFile> files = new ArrayList<>(Arrays.asList(
                new CurrencyFile("EUR/USD","C:\\Users\\Luke\\IdeaProjects\\financial-analytics\\src\\main\\java\\com\\finance\\preprocessor\\data\\eurusd")
        ));

        List<CurrencyPairDataPack> pairDataPacks = currencyReaderExtractor.readAndProcess(files, ChronoUnit.HOURS, ChronoUnit.MINUTES);
        dataBaseLoader.load(pairDataPacks);
    }
}
