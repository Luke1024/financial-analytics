package com.finance.preprocessor.utilities;

import com.finance.preprocessor.CurrencyFile;
import com.finance.preprocessor.utilities.timeFrameDataExtractor.TimeFrameExtractor;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CurrencyReaderExtractor {

    private CsvReader csvReader = new CsvReader();
    private DataPointExtractor dataPointExtractor = new DataPointExtractor();
    private TimeFrameExtractor timeFrameExtractor = new TimeFrameExtractor();


    public List<CurrencyPairDataPack> readAndProcess(List<CurrencyFile> files, ChronoUnit requiredOutputTimeFrame,
                                                     ChronoUnit inputTimeFrame) {

        List<CurrencyPairDataPack> currencyPairDataPacks = new ArrayList<>();

        for (CurrencyFile currencyFile : files) {
            currencyPairDataPacks.add(extractCurrencyPairDataFromFolder(currencyFile));
        }


        /*

        List<DataPoint> dataPoints = new ArrayList<>();

        List<List<String>> output = csvReader.read(fileName);
        dataPoints.addAll(dataPointExtractor.extract(output));

        List<DataPoint> h1DataPoints = timeFrameExtractor.extract(dataPoints, ChronoUnit.HOURS, ChronoUnit.MINUTES);

         */

        return currencyPairDataPacks;
    }

    private CurrencyPairDataPack extractCurrencyPairDataFromFolder(CurrencyFile currencyFile){

    }
}
