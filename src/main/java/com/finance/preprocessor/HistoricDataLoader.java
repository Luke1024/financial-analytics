package com.finance.preprocessor;

import com.finance.preprocessor.utilities.*;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class HistoricDataLoader {


    private CurrencyReaderExtractor currencyReaderExtractor = new CurrencyReaderExtractor();


    private final String fileName = "/home/luke/trading_app_april/src/main/java/com/finance/" +
            "preprocessor/data/eurusd/DAT_MT_EURUSD_M1_2000.csv";

    public void loadDataIntoDatabase() {
        List<CurrencyFile> files = new ArrayList<>();

        List<CurrencyPairDataPack> pairDataPacks = currencyReaderExtractor.readAndProcess(files, ChronoUnit.HOURS, ChronoUnit.MINUTES);


    }
}
