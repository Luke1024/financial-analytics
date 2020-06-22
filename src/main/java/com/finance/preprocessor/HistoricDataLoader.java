package com.finance.preprocessor;

import com.finance.preprocessor.utilities.*;
import com.finance.preprocessor.utilities.currencyreaderextractor.CurrencyReaderExtractor;
import com.finance.preprocessor.utilities.DataBaseLoader;
import com.finance.preprocessor.utilities.currencyreaderextractor.utilities.FilePathExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class HistoricDataLoader {

    @Autowired
    private CurrencyReaderExtractor currencyReaderExtractor;
    @Autowired
    private DataBaseLoader dataBaseLoader;
    @Autowired
    private FilePathExtractor filePathExtractor;

    public void loadDataIntoDatabase() {
        List<CurrencyFile> files = new ArrayList<>(Arrays.asList(
                //new CurrencyFile("EUR/GBP", "data/eurgbp"),
                new CurrencyFile("EUR/USD","data/eurusd")
        ));

        extractCurrencyPairs(files, ChronoUnit.HOURS, ChronoUnit.MINUTES);
    }

    private void extractCurrencyPairs(List<CurrencyFile> files, ChronoUnit requiredOutputTimeFrame, ChronoUnit inputTimeFrame){
        for(CurrencyFile currencyFile : files){
            extractSingleCurrencyPair(currencyFile, requiredOutputTimeFrame, inputTimeFrame);
        }
    }

    private void extractSingleCurrencyPair(CurrencyFile currencyFile, ChronoUnit requiredOutputTimeFrame, ChronoUnit inputTimeFrame){
        List<File> currencyFiles = filePathExtractor.alternative(currencyFile.getFolderPath());

        for(File file : currencyFiles){
            extractAndSaveFile(file, currencyFile, requiredOutputTimeFrame, inputTimeFrame);
        }
    }

    private void extractAndSaveFile(File file, CurrencyFile currencyFile, ChronoUnit requiredOutputTimeFrame, ChronoUnit inputTimeFrame){
        List<DataPoint> dataPoints = currencyReaderExtractor.readAndProcess(file, requiredOutputTimeFrame, inputTimeFrame);
        CurrencyPairDataPack pairPack = new CurrencyPairDataPack(currencyFile.getPairName(), requiredOutputTimeFrame, dataPoints);
        dataBaseLoader.load(Collections.singletonList(pairPack));
    }
}
